package com.sifu.sfcc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sifu.sfcc.model.CcKnowledge;

public interface CcKnowledgeDao {

	  int batchInsert(@Param("list") List<CcKnowledge> list);
	  
}
