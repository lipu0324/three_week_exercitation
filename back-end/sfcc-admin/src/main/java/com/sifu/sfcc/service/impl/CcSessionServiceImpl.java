package com.sifu.sfcc.service.impl;

import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.dto.CcScoreParam;
import com.sifu.sfcc.dto.CcWorkOrderParam;
import com.sifu.sfcc.mapper.CcArchivesMapper;
import com.sifu.sfcc.mapper.CcScoreMapper;
import com.sifu.sfcc.mapper.CcSessionRecordMapper;
import com.sifu.sfcc.mapper.CcWorkOrderMapper;
import com.sifu.sfcc.mapper.UmsAdminMapper;
import com.sifu.sfcc.mapper.UmsMemberMapper;
import com.sifu.sfcc.model.CcArchives;
import com.sifu.sfcc.model.CcScore;
import com.sifu.sfcc.model.CcSessionRecord;
import com.sifu.sfcc.model.CcSessionRecordExample;
import com.sifu.sfcc.model.UmsMember;
import com.sifu.sfcc.model.UmsMemberExample;
import com.sifu.sfcc.service.CcSessionService;
import com.sifu.sfcc.service.CcWorkOrderService;
import com.sifu.sfcc.util.CodeGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 会话管理Service实现类
 * Created by sifu on 2018/4/26.
 */
@Service
public class CcSessionServiceImpl implements CcSessionService {
    @Autowired
    private CcSessionRecordMapper sessionMapper;
    
    @Autowired
    private UmsMemberMapper memberMapper;
    
    @Autowired
    private CcArchivesMapper archivesMapper;
    
    @Autowired
    private CcWorkOrderMapper ccWorkOrderMapper;
    
    @Autowired
    private CcWorkOrderService ccWorkOrderService;
    
    @Autowired
    private UmsAdminMapper adminMapper;
    
    @Autowired
    private CcScoreMapper scoreMapper;
    
    @Override
    public List<CcSessionRecord> list(String phone , Integer pageSize , Integer pageNum) {
        PageHelper.startPage(pageNum , pageSize);
        CcSessionRecordExample example = new CcSessionRecordExample();
        CcSessionRecordExample.Criteria criteria = example.createCriteria();
        if(phone != null && !"".equals(phone)){
        	criteria.andFromNameEqualTo(phone);
        }
        
        example.setOrderByClause("time desc");

        return sessionMapper.selectByExample(example);
    }

    @Override
    public int add(CcSessionRecord label) {
    	label.setType("1");//默认单聊
        return sessionMapper.insertSelective(label);
    }

	@Override
	public int createWorkOrder(String phone,String sessionId) {
		//是否已有档案
		UmsMemberExample example = new UmsMemberExample();
		UmsMemberExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		List<UmsMember> members = memberMapper.selectByExample(example);
		CcArchives ca = null;
		String concat = "";
		if(members.size() > 0) {
			//已有档案
			ca = archivesMapper.selectByPrimaryKey(members.get(0).getArchivesId());
			concat = members.get(0).getUsername();
		}else {
			//先创建档案
			ca = new CcArchives();
			ca.setCode(CodeGeneratorUtil.generatorCode("D"));
			ca.setCreatedTime(new Date());
			ca.setCreatedBy(3L);
	        archivesMapper.insertSelective(ca);
	        
	        UmsMember member = new UmsMember();
	        member.setPhone(phone);
	        member.setArchivesId(ca.getId());
	        member.setCreateTime(new Date());
	    	member.setUpdateTime(new Date());
	    	memberMapper.insertSelective(member);
		}
		//现在有档案了，直接创建工单
		CcWorkOrderParam wo = new CcWorkOrderParam();
		wo.setAddr(ca.getAddress());
		wo.setName(ca.getName());
		wo.setPhone(phone);
		wo.setEmail(ca.getEmail());
		wo.setCreateId(3L);
		wo.setDealerId(13L); 
		wo.setConcat(concat);
		wo.setSessionId(sessionId);
		Calendar calendar2 = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar2.add(Calendar.DATE, 3);
        String threeDaysAfter = sdf2.format(calendar2.getTime());
		wo.setSubmitTime(threeDaysAfter);//创建时间延后3天
		wo.setArchivesCode(ca.getCode());
		wo.setTitle("来电未处理");
		wo.setType("0");
		
		ccWorkOrderService.create(wo);
		return 1;
	}

	@Override
	public int createScore(CcScoreParam param) {
		CcScore cs = new CcScore();
		cs.setCode(param.getCode());
		cs.setCreateTime(new Date());
		cs.setIsPerson(param.getIsPerson());
		cs.setPhone(param.getPhone());
		cs.setScore(param.getScore());
		cs.setSessionId(param.getSessionId());
		return scoreMapper.insert(cs);
	}

   
}
