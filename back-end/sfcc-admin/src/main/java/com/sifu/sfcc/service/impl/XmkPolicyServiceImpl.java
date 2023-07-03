package com.sifu.sfcc.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.sifu.sfcc.dao.XmkPolicyDao;
import com.sifu.sfcc.dto.PolicyParam;
import com.sifu.sfcc.dto.ProjectParam;
import com.sifu.sfcc.mapper.XmkPolicyMapper;
import com.sifu.sfcc.model.XmkPolicy;
import com.sifu.sfcc.model.XmkPolicyExample;
import com.sifu.sfcc.model.XmkProject;
import com.sifu.sfcc.service.XmkPolicyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XmkPolicyServiceImpl implements XmkPolicyService {

    @Value("${xmk.project.url}")
    private String XMK_PROJECT_URL;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    XmkPolicyDao xmkPolicyDao;

    @Autowired
    XmkPolicyMapper xmkPolicyMapper;

    /**
     * 分别导入不同政策集合 - 政策条数较少先不处理定时增量了
     * @param sBmType
     * @param sType
     * @param sOrgan
     * @return
     */
    @Override
    public boolean exportPolicy(Integer sBmType, Integer sType, Integer sOrgan) {
        String url = XMK_PROJECT_URL + "/zsyz/xmk/queryClassContent.do?s_bm_type="+sBmType+"&s_type="+sType+"&s_organ="+sOrgan+"";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //构造表单参数
        Map<String, String> params= new HashMap<>();
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println("responseEntity.getBody() = " + responseEntity.getBody());
        JSONObject jsonObjectData = JSON.parseObject(responseEntity.getBody(), Feature.OrderedField);
        String content = jsonObjectData.getString("content");
        List<XmkPolicy> xmkPolicyList = JSONArray.parseArray(content , XmkPolicy.class);
        System.out.println(xmkPolicyList.size());
        Long count = xmkPolicyDao.zlExportPolicy(xmkPolicyList);
        if(count > 0)
            return true;
        return false;

    }

    @Override
    public String importXmkPolicy() {
        String templateFileName = "uploads/sfcc/template-xmk-policy.xlsx";
        String filePath = "uploads/sfcc/政策库文档.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(filePath).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        XmkPolicyExample xpe = new XmkPolicyExample();
        xpe.setOrderByClause("S_CREATE_DATE DESC");
        //获取表格内容
        List<XmkPolicy> kl = xmkPolicyMapper.selectByExample(xpe);
        excelWriter.fill(kl, writeSheet);
        // 千万别忘记关闭流
        excelWriter.finish();
        return filePath;
    }

    /**
     * 导入数据前可以先清空数据
     * @return
     */
    @Override
    public boolean clearPolicy() {

        long count = xmkPolicyMapper.deleteByExample(new XmkPolicyExample());
        if(count > 0)
            return true;
        return false;
    }

    @Override
    public List<XmkPolicy> getPolicyByTitle(String title) {
        XmkPolicyExample xpe = new XmkPolicyExample();
        xpe.setOrderByClause("S_CREATE_DATE DESC");
        XmkPolicyExample.Criteria criteria = xpe.createCriteria();
        if(!StringUtils.isEmpty(title)){
            criteria.andSTitleLike("%" + title + "%");
        }
        return xmkPolicyMapper.selectByExample(xpe);
    }

	@Override
	public PolicyParam getPolicyCount(String location) {
		JSONObject data= JSON.parseObject(location);
		String city = data.getString("city");
		String title = data.getString("title");
		List<XmkPolicy> xp = xmkPolicyDao.getPolicyCount(city,title);
		PolicyParam pp = new PolicyParam();
		if(xp.size() > 0 ) {
			pp.setCount(xp.size());
			pp.setPolicyTitle(xp.get(0).getsCreateUserName());
		}else {
			pp.setCount(0);
		}
		return pp;
	}

	@Override
	public PolicyParam getPolicyRecommend(String location, Integer pageNum) {
		JSONObject data= JSON.parseObject(location);
		String city = data.getString("city");
		PolicyParam pp = xmkPolicyDao.getPolicyRecommend(city,pageNum);
		return pp;
	}
}
