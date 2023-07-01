package com.sifu.sfcc.service.impl;

import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.dao.UmsMemberDao;
import com.sifu.sfcc.mapper.UmsMemberMapper;
import com.sifu.sfcc.model.UmsMember;
import com.sifu.sfcc.model.UmsMemberExample;
import com.sifu.sfcc.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会员等级管理Service实现类
 * Created by sifu on 2018/4/26.
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private UmsMemberMapper memberMapper;
    
    @Autowired
    private UmsMemberDao memberDao;
    @Override
    public List<UmsMember> list(String name , String phone, String startTime,String endTime,long label,String startCount,String endCount,String salesman, Integer status,Integer pageSize , Integer pageNum) {
        PageHelper.startPage(pageNum , pageSize);
        UmsMemberExample example = new UmsMemberExample();
        UmsMemberExample.Criteria criteria = example.createCriteria();
        if(name != null && !"".equals(name)){
            criteria.andUsernameLike("%" +name+ "%");    
        }
        
        if(phone != null && !"".equals(phone)){
            criteria.andPhoneLike("%" +phone+ "%");   
        }
        
        if(startTime != null && !"".equals(startTime) && !"".equals(endTime)){
        	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");//日期格式
            try {
				criteria.andCreateTimeBetween(format.parse(startTime), format.parse(endTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }
        
        if(label != -1 ){
        	criteria.andLabelEqualTo(label);
        }
        
        if(startCount != null && !"".equals(startCount) && endCount != null && !"".equals(endCount)){
        	criteria.andCountGreaterThanOrEqualTo(Integer.valueOf(startCount));
        	criteria.andCountLessThanOrEqualTo(Integer.valueOf(endCount));
        }else if(startCount != null && !"".equals(startCount) ){
        	criteria.andCountGreaterThanOrEqualTo(Integer.valueOf(startCount));
        }else if(endCount != null && !"".equals(endCount)){
        	criteria.andCountLessThanOrEqualTo(Integer.valueOf(endCount));
        }
        
        if(null != status && status != -1 ){
        	criteria.andStatusEqualTo(status);
        }
        
        if(salesman != null && !"".equals(salesman) ){
        	criteria.andSalesmanLike(salesman);
        }
        
        example.setOrderByClause("create_time desc");

        return memberMapper.selectByExample(example);
    }

    @Override
    public int add(UmsMember member) {
    	member.setCreateTime(new Date());
    	member.setUpdateTime(new Date());
    	//查询是否有相同电话
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andPhoneEqualTo(member.getPhone());
        List<UmsMember> umsAdminList = memberMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
        	member.setCount(null);
        	return memberMapper.updateByExampleSelective(member, example);
        }else {
        	return memberMapper.insertSelective(member);
        }
    	
    }

    @Override
    public int update(UmsMember member) {
        return memberMapper.updateByPrimaryKeySelective(member);
    }

    @Override
    public int delete(Long id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UmsMember get(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

	@Override
	public int ban(Long id, Integer status) {
		UmsMember um = new UmsMember();
		um.setStatus(status);
		um.setId(id);
		return memberMapper.updateByPrimaryKeySelective(um);
	}

	@Override
	public int batchBan(List<Long> ids) {
		UmsMemberExample example = new UmsMemberExample();
		example.createCriteria().andIdIn(ids);
		UmsMember um = new UmsMember();
		um.setStatus(0);
		return memberMapper.updateByExampleSelective(um, example);
	}

	@Override
	public int updateBatch(List<UmsMember> member) {
		
		return memberDao.updateBatch(member);
	}

	@Override
	public long total() {
		// TODO Auto-generated method stub
		UmsMemberExample example = new UmsMemberExample();
//		example.createCriteria().andStatusEqualTo(1);
		return memberMapper.countByExample(example);
	}

	@Override
	public int addBatch(List<UmsMember> member) {
		List<UmsMember> adds = new ArrayList<>();
		int res = 1;
		member.forEach(v->{
			//查询是否有相同电话
	        UmsMemberExample example = new UmsMemberExample();
	        example.createCriteria().andPhoneEqualTo(v.getPhone());
	        List<UmsMember> umsAdminList = memberMapper.selectByExample(example);
	        if (umsAdminList.size() > 0) {
	        	UmsMember um = umsAdminList.get(0);
	        	um.setUpdateTime(new Date());
	        	um.setArchivesId(v.getArchivesId());
	        	um.setUsername(ObjectUtils.isEmpty(v.getUsername())?um.getUsername():v.getUsername());
	        	memberMapper.updateByExampleSelective(um, example);
	        	
	        }else {
	        	v.setCreateTime(new Date());
	        	v.setCount(0);
	        	v.setStatus(1);
	        	v.setUpdateTime(new Date());
	        	adds.add(v);
	        }
		});
		if(adds.size() > 0 ) {
			res = memberDao.addBatch(adds);
		}
		return res;
	}
}
