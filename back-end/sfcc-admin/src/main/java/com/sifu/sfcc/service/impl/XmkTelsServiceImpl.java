package com.sifu.sfcc.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.mapper.XmkTelsMapper;
import com.sifu.sfcc.model.XmkTels;
import com.sifu.sfcc.model.XmkTelsExample;
import com.sifu.sfcc.model.XmkTelsExample.Criteria;
import com.sifu.sfcc.service.XmkTelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class XmkTelsServiceImpl implements XmkTelsService {

    @Autowired
    XmkTelsMapper xmkTelsMapper;

	@Override
	public List<XmkTels> getTels(String location) {
		JSONObject data= JSON.parseObject(location);
		String city = data.getString("city");
		String company = data.getString("company");
		XmkTelsExample ex = new XmkTelsExample();
		Criteria criteria = ex.createCriteria();
		if(null != city && city.length() > 0) {
			criteria.andTerritoryLike(city);
		}
		
		if(null != company && company.length() > 0) {
			criteria.andCompanyLike(company);
		}
		
		List<XmkTels> tels = xmkTelsMapper.selectByExample(ex);
		return tels;
	}

	@Override
	public List<XmkTels> getTelsByCompany(String company) {

		XmkTelsExample ex = new XmkTelsExample();
		Criteria criteria = ex.createCriteria();
		if(!StringUtils.isEmpty(company)) {
			criteria.andCompanyLike(company);
		}
		List<XmkTels> tels = xmkTelsMapper.selectByExample(ex);
		return tels;
	}

}
