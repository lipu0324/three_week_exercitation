package com.sifu.sfcc.service.impl;

import com.sifu.sfcc.mapper.CcScoreMapper;
import com.sifu.sfcc.model.CcScore;
import com.sifu.sfcc.model.CcScoreExample;
import com.sifu.sfcc.service.CcScoreService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 问卷系统管理Service实现类
 * Created by sifu on 2022/3/7.
 */
@Service
public class CcScoreServiceImpl implements CcScoreService {
    @Autowired
    private CcScoreMapper scoreMapper;
    
	@Override
	public long scoreCount() {
		// TODO Auto-generated method stub
		CcScoreExample example = new CcScoreExample();
		return scoreMapper.countByExample(example);
	}

	@Override
	public Map<String,Long> count(String startTime,String endTime,String code) {
		CcScoreExample example = new CcScoreExample();
		CcScoreExample.Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"); 
			try {
				criteria.andCreateTimeBetween(format.parse(startTime), format.parse(endTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
			criteria.andCodeEqualTo(code);
		}
		
		List<CcScore> ss = scoreMapper.selectByExample(example);
		Map<String,Long> map = new HashMap<>();
		ss.forEach(v->{
			String score = v.getScore();
			if(score.contains("一")) {
				if(map.containsKey("1")) {
					//证明已经存在
					long value = map.get("1");
					map.put("1", value+1);
				}else {
					map.put("1", 1L);
				}
			 }else if(score.contains("二") || score.contains("两")) {
				if(map.containsKey("2")) {
					//证明已经存在
					long value = map.get("2");
					map.put("2", value+1);
				}else {
					map.put("2", 1L);
				}
			 }else if(score.contains("三")) {
				if(map.containsKey("3")) {
					//证明已经存在
					long value = map.get("3");
					map.put("3", value+1);
				}else {
					map.put("3", 1L);
				}
			 }else if(score.contains("四")) {
				if(map.containsKey("4")) {
					//证明已经存在
					long value = map.get("4");
					map.put("4", value+1);
				}else {
					map.put("4", 1L);
				}
			 }else{
				if(map.containsKey("5")) {
					//证明已经存在
					long value = map.get("5");
					map.put("5", value+1);
				}else {
					map.put("5", 1L);
				}
			 }
			
		});
		
		return map;
	}
    
}
