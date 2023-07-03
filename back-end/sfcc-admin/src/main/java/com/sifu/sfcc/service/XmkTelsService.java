package com.sifu.sfcc.service;

import java.util.List;
import com.sifu.sfcc.model.XmkTels;


public interface XmkTelsService {


	List<XmkTels> getTels(String location);

	List<XmkTels> getTelsByCompany(String company);
}
