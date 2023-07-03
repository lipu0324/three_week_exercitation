package com.sifu.sfcc.dao;

import java.util.List;
import com.sifu.sfcc.model.UmsMember;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberDao {

	int updateBatch(@Param("list") List<UmsMember> list);
	
	int addBatch(@Param("list") List<UmsMember> list);
	
	List<UmsMember> getMember(String archivesCode);
}
