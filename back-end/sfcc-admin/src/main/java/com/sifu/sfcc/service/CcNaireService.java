package com.sifu.sfcc.service;

import com.sifu.sfcc.dto.CcNaireQuestionParam;
import com.sifu.sfcc.model.CcNaire;
import com.sifu.sfcc.model.CcNaireAnswer;
import com.sifu.sfcc.model.CcNaireQuestion;

import java.util.List;
import java.util.Map;

/**
 * 问卷管理Service
 * Created by sifu on 2021/10/21.
 */
public interface CcNaireService {
    /**
     * 问卷管理
     */
    List<CcNaire> listNaire(String title , String status, Integer pageSize , Integer pageNum);
    
    int addNaire(CcNaire naire);

    int updateNaire(CcNaire naire);

    int deleteNaire(Long id);

    CcNaire getNaire(Long id);
    
    /**
     * 问题管理
     */
    List<CcNaireQuestionParam> listNoQuestion(Long naireId,Long questionId);
    
    List<CcNaireQuestionParam> listQuestion(Long naireId , Integer pageSize , Integer pageNum);
    
    int addQuestion(CcNaireQuestion naire);

    int updateQuestion(CcNaireQuestion naire);

    int deleteQuestion(Long id);

    CcNaireQuestion getQuestion(Long id);
    
    /**
     * 问卷答题管理
     */
    
    List<CcNaireAnswer> listAnswer(Long questionId , Integer pageSize , Integer pageNum);
    
    int addAnswer(CcNaireAnswer naire);

    int updateAnswer(CcNaireAnswer naire);

    int deleteAnswer(Long id);

    CcNaireAnswer getAnswer(Long id);
    
    Map<String,String> getQuestion(String phone);
    
    Map<String,String> getNextQuestion(String phone,String id,String type,String answer,String isNo);
}
