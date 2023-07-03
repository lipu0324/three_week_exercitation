package com.sifu.sfcc.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.dao.CcArchivesDao;
import com.sifu.sfcc.dao.UmsAdminDao;
import com.sifu.sfcc.dto.AdminParam;
import com.sifu.sfcc.dto.CcArchivesExport;
import com.sifu.sfcc.dto.CcArchivesParam;
import com.sifu.sfcc.mapper.CcArchivesMapper;
import com.sifu.sfcc.mapper.UmsAdminMapper;
import com.sifu.sfcc.mapper.UmsMemberMapper;
import com.sifu.sfcc.model.CcArchives;
import com.sifu.sfcc.model.CcArchivesContent;
import com.sifu.sfcc.model.CcArchivesExample;
import com.sifu.sfcc.model.UmsAdmin;
import com.sifu.sfcc.model.UmsMember;
import com.sifu.sfcc.model.UmsMemberExample;
import com.sifu.sfcc.service.CcArchivesContentService;
import com.sifu.sfcc.service.CcArchivesService;
import com.sifu.sfcc.service.UmsAdminService;
import com.sifu.sfcc.util.CodeGeneratorUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 知识库管理Service实现类
 * Created by sifu on 2021/11/12.
 */
@Service
public class CcArchivesServiceImpl implements CcArchivesService {
    @Autowired
    private CcArchivesMapper archivesMapper;
    
    @Autowired
    private UmsMemberMapper memberMapper;
    
    @Autowired
    private UmsAdminDao adminDao;
    
    @Autowired
    private UmsAdminMapper adminMapper;
    
    @Autowired
    private UmsAdminService adminService;
    
    @Autowired
    private CcArchivesDao archivesDao;
    
    @Autowired
    private CcArchivesContentService contentService;
    
    @Override
    public List<CcArchivesParam> list(String name ,String code,String urgency, String nameOrUrgency,Integer pageSize , Integer pageNum) {
        PageHelper.startPage(pageNum , pageSize);
        List<CcArchivesParam> acp = archivesDao.getArchives(name,code,urgency,nameOrUrgency);
        acp.forEach(v->{
        	String label = v.getLabel();
        	if(null != label && !"".equals(label)) {
        		String[] split = label.split(",");
            	Set<String> set = new HashSet<>();
            	for(int i=0;i<split.length;i++) {
            		if(split[i] != "") {
            			set.add(split[i]);
            		}
            	}
            	String str = String.join(",", set);
            	System.out.println(str.indexOf(","));
            	int index = str.indexOf(",") == 0?1: 0;
            	System.out.println(index);
            	v.setLabel(str.substring(index));
        	}
        });
        
        return acp;
    }

    @Override
    public int add(CcArchives label) {
    	label.setCreatedBy(adminService.getCurrentUserAdmin().getId());
    	label.setCode(CodeGeneratorUtil.generatorCode("D"));
        return archivesMapper.insertSelective(label);
    }

    @Override
    public int update(CcArchives label) {
    	label.setUpdatedBy(adminService.getCurrentUserAdmin().getId());
        return archivesMapper.updateByPrimaryKeySelective(label);
    }

    @Override
    public int delete(Long id) {
        return archivesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CcArchivesParam get(Long id) {
    	CcArchivesParam cap = new CcArchivesParam();
    	//获取档案基础信息
    	CcArchives ca = archivesMapper.selectByPrimaryKey(id);
    	BeanUtils.copyProperties(ca, cap);
    	//获取档案内容
    	List<CcArchivesContent> cac = contentService.list(id);
    	UmsMemberExample example = new UmsMemberExample();
    	UmsMemberExample.Criteria criteria = example.createCriteria();
    	criteria.andArchivesIdEqualTo(id);
    	example.setOrderByClause("update_time desc");
    	List<UmsMember> member = memberMapper.selectByExample(example);
    	cap.setCalls(member);
    	cap.setContents(cac);
    	
    	AdminParam ap = adminDao.selectAdminById(ca.getCreatedBy());
    	cap.setDeptName(ap.getDeptName());
        return cap ;
    }

	@Override
	public List<CcArchives> list() {
		CcArchivesExample example = new CcArchivesExample();
		return archivesMapper.selectByExample(example);
	}

	@Override
	public CcArchivesParam getArchivesByPhone(String phone) {
		//新增客服服务次数
		UmsAdmin admin = adminMapper.selectByPrimaryKey(adminService.getCurrentUserAdmin().getId());
		admin.setCount(admin.getCount()+1);
		adminMapper.updateByPrimaryKeySelective(admin);
		
		
		UmsMemberExample example = new UmsMemberExample();
		UmsMemberExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		List<UmsMember> member = memberMapper.selectByExample(example);
		CcArchivesParam param = new CcArchivesParam();
		
		if(!member.isEmpty() && member.size() > 0) {
			param = getOne(member.get(0));
		}else {
			CcArchives archives = new CcArchives();
			archives.setCode(CodeGeneratorUtil.generatorCode("D"));
			archives.setCreatedTime(new Date());
			archives.setCreatedBy(adminService.getCurrentUserAdmin().getId());
			archivesMapper.insertSelective(archives);
			
			UmsMember m = new UmsMember();
			m.setPhone(phone);
			m.setArchivesId(archives.getId());
			m.setCount(1);
			m.setCreateTime(new Date());
			m.setUpdateTime(new Date());
			memberMapper.insertSelective(m);
			BeanUtils.copyProperties(archives, param);
			
			AdminParam ap = adminDao.selectAdminById(adminService.getCurrentUserAdmin().getId());
			param.setSitDown(ap.getCode());
			param.setDeptName(ap.getDeptName());
			
		}
		return param;
	}
	
	public CcArchivesParam getOne(UmsMember member) {
    	CcArchivesParam cap = new CcArchivesParam();
    	//获取档案基础信息
    	CcArchives ca = archivesMapper.selectByPrimaryKey(member.getArchivesId());
    	BeanUtils.copyProperties(ca, cap);
    	AdminParam ap = adminDao.selectAdminById(ca.getCreatedBy());
    	cap.setSitDown(ap.getCode());
    	cap.setDeptName(ap.getDeptName());
    	
    	//修改当前用户会话次数
    	member.setCount(member.getCount() + 1);
    	member.setUpdateTime(new Date());
    	memberMapper.updateByPrimaryKeySelective(member);
    	
    	//获取档案内容
    	List<CcArchivesContent> cac = contentService.list(member.getArchivesId());
    	UmsMemberExample example = new UmsMemberExample();
    	UmsMemberExample.Criteria criteria = example.createCriteria();
    	criteria.andArchivesIdEqualTo(member.getArchivesId());
    	example.setOrderByClause("update_time desc");
    	List<UmsMember> members = memberMapper.selectByExample(example);
    	
    	cap.setCalls(members);
    	cap.setContents(cac);
        return cap ;
    }

	@Override
	public String export(String name, String code, String urgency, String nameOrUrgency) {
		List<CcArchivesExport> kl = archivesDao.getArchivesExport(name,code,urgency,nameOrUrgency);
		
  		String templateFileName = "/uploads/sfcc/archivesTemplate.xlsx";
  		String fileName = "/uploads/sfcc/客户档案.xlsx";
  		ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
  	    WriteSheet writeSheet = EasyExcel.writerSheet().build();
//          //获取表格内容
//        List<CcArchivesParam> kl = archivesDao.getArchives(name,code,urgency,nameOrUrgency);
//        System.out.println("========"+ kl.size() +"========");
        kl.forEach(v->{
        	String label = v.getLabel();
        	if(null != label && !"".equals(label)) {
        		String[] split = label.split(",");
            	Set<String> set = new HashSet<>();
            	for(int i=0;i<split.length;i++) {
            		if(split[i] != "") {
            			set.add(split[i]);
            		}
            	}
            	String str = String.join(",", set);
            	System.out.println(str.indexOf(","));
            	int index = str.indexOf(",") == 0?1: 0;
            	System.out.println(index);
            	v.setLabel(str.substring(index));
        	}
        	
        	if("0".equals(v.getUrgency())) {
        		v.setUrgency("普通");
        	}else if("1".equals(v.getUrgency())){
        		v.setUrgency("紧急");
        	}else if("2".equals(v.getUrgency())){
        		v.setUrgency("非常紧急");
        	}
        	
    		String createTime = v.getCreatedTime();
    		if(!ObjectUtil.isEmpty(createTime)) {
    			v.setCreatedTime(createTime.substring(0,10));
    		}
        	
        });
        excelWriter.fill(kl, writeSheet);
          // 千万别忘记关闭流
        excelWriter.finish();   
		return fileName;
	}

	@Override
	public long total() {
		// TODO Auto-generated method stub
		CcArchivesExample example = new CcArchivesExample();
		return archivesMapper.countByExample(example);
	}

	@Override
    public CcArchivesParam getArchives(String code) {
    	CcArchivesParam cap = new CcArchivesParam();
    	//获取档案基础信息
    	CcArchivesExample example = new CcArchivesExample();
    	CcArchivesExample.Criteria criteria = example.createCriteria();
    	criteria.andCodeEqualTo(code);
    	List<CcArchives> as = archivesMapper.selectByExample(example);
    	CcArchives ca = null;
    	if(as.size() > 0) {
    		ca = as.get(0);
        	BeanUtils.copyProperties(ca, cap);
        	//获取档案内容
        	List<CcArchivesContent> cac = contentService.list(ca.getId());
        	UmsMemberExample memberExample = new UmsMemberExample();
        	UmsMemberExample.Criteria memberCriteria = memberExample.createCriteria();
        	memberCriteria.andArchivesIdEqualTo(ca.getId());
        	
        	example.setOrderByClause("update_time desc");
        	List<UmsMember> member = memberMapper.selectByExample(memberExample);
        	cap.setCalls(member);
        	cap.setContents(cac);
        	
        	AdminParam ap = adminDao.selectAdminById(ca.getCreatedBy());
        	cap.setDeptName(ap.getDeptName());
    	}
        return cap ;
    }

	@Override
	public Map<String,Long> getArchivesCount(String label, String startTime, String endTime) {
		List<CcArchivesParam> ps = archivesDao.getArchivesCount(label, startTime, endTime);
		Map<String,Long> result = new HashMap<>();
		ps.forEach(v->{
			Date createdTime = v.getCreatedTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(createdTime);
			if(null != result.get(dateString)) {
				Long count = result.get(dateString) + 1;
				result.put(dateString, count);
			}else {
				result.put(dateString, 1L);
			}
			
		});
		
		return result;
	}

	@Override
	public long labelCount(String lable) {
		
		return archivesDao.labelCount(lable);
	}

}
