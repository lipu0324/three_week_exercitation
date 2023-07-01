package com.sifu.sfcc.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.common.utils.DateUtil;
import com.sifu.sfcc.dao.XmkProjectDao;
import com.sifu.sfcc.dto.CcArchivesParam;
import com.sifu.sfcc.dto.ProjectParam;
import com.sifu.sfcc.mapper.XmkProjectMapper;
import com.sifu.sfcc.model.XmkProject;
import com.sifu.sfcc.model.XmkProjectExample;
import com.sifu.sfcc.service.XmkProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XmkProjectServiceImpl implements XmkProjectService {

    @Value("${xmk.project.url}")
    private String XMK_PROJECT_URL;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    XmkProjectMapper xmkProjectMapper;

    @Autowired
    XmkProjectDao xmkProjectDao;

    private static final String template = "template-xmk.xlsx";
    /**
     * 手动导入全部数据
     * @return
     */
    @Override
    public boolean exportProject() {

        //导入前先清空全部内容
        xmkProjectMapper.deleteByExample(new XmkProjectExample());

        String url = XMK_PROJECT_URL + "/zsyz/xmk/projectList.do";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //构造表单参数
        Map<String, String> params= new HashMap<>();
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println("responseEntity.getBody() = " + responseEntity.getBody());
        JSONObject jsonObjectData = JSON.parseObject(responseEntity.getBody(), Feature.OrderedField);
        String content = jsonObjectData.getString("content");
        List<XmkProject> xmkProjectList = JSONArray.parseArray(content , XmkProject.class);
        System.out.println(xmkProjectList.size());
        Long count = xmkProjectDao.zlExportProject(xmkProjectList);
        if(count > 0)
            return true;
        return false;
    }

    /**
     * 增量导入项目工程 - 定时（每周）
     * @return
     */
    @Override
    public boolean zlExportProject() {
        String url = XMK_PROJECT_URL + "/zsyz/xmk/projectListByDate.do";
        List<XmkProject> xmkProjectList = xmkProjectDao.queryXmkProject();
        System.out.println("xmkProjectList = " + xmkProjectList);
        if(null != xmkProjectList && xmkProjectList.size() > 0){
            XmkProject xmkProject = xmkProjectList.get(0);
            Date publishTime = xmkProject.getsPublishTime();//最后的时间点
            long time = publishTime.getTime() + 1000;
            String startDate = DateUtil.getFormatDate(new Date(time));
            System.out.println("startDate = " + startDate);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            //构造表单参数
            Map<String, String> params= new HashMap<>();
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(params, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url + "?startDate=" + startDate, requestEntity, String.class);

            System.out.println("responseEntity.getBody() = " + responseEntity.getBody());
            JSONObject jsonObjectData = JSON.parseObject(responseEntity.getBody(), Feature.OrderedField);
            String content = jsonObjectData.getString("content");

            List<XmkProject> zlXmkProjectList = JSONArray.parseArray(content , XmkProject.class);
            System.out.println(zlXmkProjectList.size());
            if(null != zlXmkProjectList && zlXmkProjectList.size() > 0){
                Long count = xmkProjectDao.zlExportProject(zlXmkProjectList);
                System.out.println("count = " + count);
                if(count > 0) return true;
            }
        }
        return false;
    }

    /**
     * 根据项目名称模糊查询项目
     * @param pageSize
     * @param pageNum
     * @param projectName
     * @return
     */
    @Override
    public List<XmkProject> getProjectByName(Integer pageSize, Integer pageNum, String projectName) {
        PageHelper.startPage(pageNum , pageSize);
        XmkProjectExample xpe = new XmkProjectExample();
        xpe.setOrderByClause("S_PUBLISH_TIME DESC");
        XmkProjectExample.Criteria criteria = xpe.createCriteria();
        if(!StringUtils.isEmpty(projectName)){
            criteria.andSXmNameLike("%" + projectName + "%");
        }
        return xmkProjectMapper.selectByExample(xpe);
    }

    /**
     * 向.xsl文件中导入数据库数据
     * @return
     */
    @Override
    public String importXmkProject() {
        String templateFileName = "uploads/sfcc/template-xmk.xlsx";
        String filePath = "uploads/sfcc/项目库文档.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(filePath).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        XmkProjectExample xpe = new XmkProjectExample();
        xpe.setOrderByClause("S_PUBLISH_TIME DESC");
        //获取表格内容
        List<XmkProject> kl = xmkProjectMapper.selectByExample(xpe);
        excelWriter.fill(kl, writeSheet);
        // 千万别忘记关闭流
        excelWriter.finish();
        return filePath;
    }

	@Override
	public ProjectParam getCountByLocation(String location) {
		JSONObject data= JSON.parseObject(location);
		String city = data.getString("city");
		String area = data.getString("area");
		List<XmkProject> xp = xmkProjectDao.getCountByLocation(city, area);
		ProjectParam pp = new ProjectParam();
		if(xp.size() > 0 ) {
			pp.setCount(xp.size());
			pp.setProjectName(xp.get(0).getsXmName());
		}else {
			pp.setCount(0);
		}
		return pp;
	}

	@Override
	public ProjectParam getRecommendType(String location, Integer pageNum) {
		JSONObject data= JSON.parseObject(location);
		String city = data.getString("city");
		String area = data.getString("area");
		ProjectParam pp = xmkProjectDao.getRecommendType(city, area, pageNum);
		return pp;
	}

	@Override
	public ProjectParam getTotal(String location) {
		JSONObject data= JSON.parseObject(location);
		String city = data.getString("city");
		String area = data.getString("area");
		String projectType = data.getString("projectType");
		ProjectParam pp =  xmkProjectDao.getTotal(city, area, projectType);
		return pp;
	}

	@Override
	public ProjectParam getRecommend(String location, Integer pageNum) {
		JSONObject data= JSON.parseObject(location);
		String city = data.getString("city");
		String area = data.getString("area");
        String type = data.getString("projectType");
		ProjectParam pp =  xmkProjectDao.getRecommend(city, area, type,pageNum);
		return pp;
	}

	@Override
	public ProjectParam getLocation(String name, Integer pageNum) {
		ProjectParam pp = xmkProjectDao.getLocation(name, pageNum);
		return pp;
	}

}
