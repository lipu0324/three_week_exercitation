package com.sifu.sfcc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sifu.sfcc.dto.CcNaireQuestionParam;
import com.sifu.sfcc.model.CcNaireAnswer;
import com.sifu.sfcc.model.CcNaireQuestion;

public interface CcNaireDao {

	List<CcNaireQuestion> getQuestion(String phone);
	
	List<CcNaireAnswer> getAnswer(String phone);
	
	List<CcNaireQuestionParam> listQuestion(@Param(value="naireId")Long naireId);
	  
	List<CcNaireQuestionParam> listNoQuestion(@Param(value="naireId")Long naireId,@Param(value="questionId") Long questionId);
	
	List<CcNaireQuestion> getNaireQuestion(@Param(value="naireId")Long naireId,@Param(value="id")Long id);
}
