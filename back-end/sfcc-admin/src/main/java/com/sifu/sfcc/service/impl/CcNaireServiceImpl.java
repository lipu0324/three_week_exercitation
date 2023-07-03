package com.sifu.sfcc.service.impl;

import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.dao.CcNaireDao;
import com.sifu.sfcc.dto.CcNaireQuestionParam;
import com.sifu.sfcc.mapper.CcCallTaskMapper;
import com.sifu.sfcc.mapper.CcNaireAnswerMapper;
import com.sifu.sfcc.mapper.CcNaireMapper;
import com.sifu.sfcc.mapper.CcNaireQuestionMapper;
import com.sifu.sfcc.model.CcCallTask;
import com.sifu.sfcc.model.CcCallTaskExample;
import com.sifu.sfcc.model.CcNaire;
import com.sifu.sfcc.model.CcNaireAnswer;
import com.sifu.sfcc.model.CcNaireAnswerExample;
import com.sifu.sfcc.model.CcNaireExample;
import com.sifu.sfcc.model.CcNaireQuestion;
import com.sifu.sfcc.model.CcNaireQuestionExample;
import com.sifu.sfcc.service.CcNaireService;
import com.sifu.sfcc.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问卷系统管理Service实现类
 * Created by sifu on 2018/4/26.
 */
@Service
public class CcNaireServiceImpl implements CcNaireService {
    @Autowired
    private CcNaireMapper naireMapper;
    
    @Autowired
    private CcCallTaskMapper taskMapper;
    
    @Autowired
    private CcNaireAnswerMapper answerMapper;
    
    @Autowired
    private CcNaireQuestionMapper questionMapper;
    
    @Autowired
    private CcNaireDao naireDao;
    
    @Autowired
    private UmsAdminService adminService;

	@Override
	public List<CcNaire> listNaire(String title, String status,Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum , pageSize);
		CcNaireExample example = new CcNaireExample();
		CcNaireExample.Criteria criteria = example.createCriteria();
        if(title != null && !"".equals(title)){
        	criteria.andTitleLike("%" +title+ "%");
        }
        if(status != null && !"".equals(status)){
        	criteria.andStatusEqualTo(Integer.valueOf(status));
        }
        example.setOrderByClause("created_time desc");

        return naireMapper.selectByExample(example);
	}

	@Override
	public int addNaire(CcNaire naire) {
		naire.setCreatedBy(adminService.getCurrentUserAdmin().getId());
		naire.setCreatedTime(new Date());
		naire.setStatus(0);
		return naireMapper.insertSelective(naire);
	}

	@Override
	public int updateNaire(CcNaire naire) {
		return naireMapper.updateByPrimaryKeySelective(naire);
	}

	@Override
	public int deleteNaire(Long id) {
		//关联任务的问卷不能删除
		CcCallTaskExample example = new CcCallTaskExample();
		CcCallTaskExample.Criteria criteria = example.createCriteria();
		criteria.andNaireIdEqualTo(id);
		
		List<CcCallTask> tasks = taskMapper.selectByExample(example);
		if(tasks.size() > 0) {
			return 0;
		}
		
		return naireMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CcNaire getNaire(Long id) {
		return naireMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CcNaireQuestionParam> listQuestion(Long naireId, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum , pageSize);
        return naireDao.listQuestion(naireId);
	}

	@Override
	public int addQuestion(CcNaireQuestion naire) {
		return questionMapper.insertSelective(naire);
	}

	@Override
	public int updateQuestion(CcNaireQuestion naire) {
		
		Integer isFirst = naire.getIsFirst();
		if(isFirst != null && isFirst == 1) {
			//是第一个问题，判断是否已经存在第一个问题，做修改的时候排除第一个问题
			CcNaireQuestionExample example = new CcNaireQuestionExample();
			CcNaireQuestionExample.Criteria criteria = example.createCriteria();
			criteria.andNaireIdEqualTo(naire.getNaireId());
			criteria.andIsFirstEqualTo(1);
			criteria.andIdNotEqualTo(naire.getId());
			List<CcNaireQuestion> qs = questionMapper.selectByExample(example);
			if(qs.size() > 0) {
				return 0;
			}
			//是第一个问题，判断是否已经被别人选择
			List<CcNaireQuestion> qs1 = naireDao.getNaireQuestion(naire.getNaireId(), naire.getId());
			if(qs1.size() > 0) {
				return -1;
			}
		}
		questionMapper.updateByPrimaryKeySelective(naire);
		
		//判断是否配置完成，两种情况，1，存在第一个问题
		naireStatus(naire.getNaireId());
		return 1;
	}
	
	private void naireStatus(Long naireId) {
		CcNaireQuestionExample example = new CcNaireQuestionExample();
		CcNaireQuestionExample.Criteria criteria = example.createCriteria();
		criteria.andNaireIdEqualTo(naireId);
		criteria.andIsFirstEqualTo(1);
		List<CcNaireQuestion> qs = questionMapper.selectByExample(example);
		if(qs.size() > 0) {
			//存在第一个问题，修改对应问卷状态
			CcNaire na = new CcNaire();
			na.setId(naireId);
			na.setStatus(1);
			naireMapper.updateByPrimaryKeySelective(na);
		}else {
			//不存在第一个问题，修改对应问卷状态
			CcNaire na = new CcNaire();
			na.setId(naireId);
			na.setStatus(0);
			naireMapper.updateByPrimaryKeySelective(na);
		}
	}

	@Override
	public int deleteQuestion(Long id) {
		CcNaireQuestion question = questionMapper.selectByPrimaryKey(id);
		Long naireId = question.getNaireId();
		int count = 0;
		List<CcNaireQuestion> qs1 = naireDao.getNaireQuestion(naireId, id);
		if(qs1.size() > 0) {
			return -1;
		}else {
			count = questionMapper.deleteByPrimaryKey(id);
			//判断是否配置完成，两种情况，1，存在第一个问题
			naireStatus(naireId);
		}
		
		return count;
	}

	@Override
	public CcNaireQuestion getQuestion(Long id) {
		return questionMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CcNaireAnswer> listAnswer(Long questionId, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum , pageSize);
		CcNaireAnswerExample example = new CcNaireAnswerExample();
		CcNaireAnswerExample.Criteria criteria = example.createCriteria();
        if(questionId != 0){
        	criteria.andQuestionIdEqualTo(questionId);
        }
        example.setOrderByClause("time desc");

        return answerMapper.selectByExample(example);
	}

	@Override
	public int addAnswer(CcNaireAnswer naire) {
		naire.setTime(new Date());
		return answerMapper.insertSelective(naire);
	}

	@Override
	public int updateAnswer(CcNaireAnswer naire) {
		return answerMapper.updateByPrimaryKeySelective(naire);
	}

	@Override
	public int deleteAnswer(Long id) {
		return answerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CcNaireAnswer getAnswer(Long id) {
		return answerMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String,String> getQuestion(String phone) {
		//查找第一个问题
		List<CcNaireQuestion> cq = naireDao.getQuestion(phone);
		Map<String,String> result = new HashMap<>();
		if(cq.size() > 0) {
			CcNaireQuestion question = cq.get(0);
			//查询欢迎语
			CcNaire naire = naireMapper.selectByPrimaryKey(question.getNaireId());
			String welText = naire.getWelText();
			result.put("welText", welText);
			result.put("title", question.getTitle());
			result.put("type", question.getType()+"");
			result.put("id", question.getId()+"");
			result.put("isEnd", "0");
			result.put("phone", phone);
		}
		
		return result;
	}

	@Override
	public List<CcNaireQuestionParam> listNoQuestion(Long naireId,Long questionId) {
		//已经被选的问题过滤掉，
		
		return naireDao.listNoQuestion(naireId,questionId);
	}

	@Override
	public Map<String, String> getNextQuestion(String phone, String id, String type, String answer,String isNo) {
		Map<String,String> result = new HashMap<>();
		//根据id 存储本次答案
		CcNaireAnswer na = new CcNaireAnswer();
		na.setQuestionId(Long.parseLong(id));
		na.setTime(new Date());
		na.setUserPhone(phone);
		na.setUserAnswer(answer);
		na.setIsNo(isNo);
		answerMapper.insert(na);
		
		//根据id 查找下个问题和结束语
		CcNaireQuestion cq = questionMapper.selectByPrimaryKey(Long.parseLong(id));
		if(cq.getIsTrueId() == null && cq.getIsFalseId() == null && cq.getNextId() == null) {
			//查询结束语
			CcNaire naire = naireMapper.selectByPrimaryKey(cq.getNaireId());
			result.put("title", "");
			result.put("type", "");
			result.put("id", naire.getId()+"");
			result.put("isEnd", "1");
			result.put("endText", naire.getEndText());
		}else {
			Long nextId = null;
			if(type.equals("0")) {
				//代表问题是是否类型
				if("0".equals(isNo)) {
					nextId = cq.getIsTrueId();
				}else if("1".equals(isNo)) {
					nextId = cq.getIsFalseId();
				}
			}else {
				nextId = cq.getNextId();
			}
			
			if(nextId != null) {
				CcNaireQuestion nextQuestion = questionMapper.selectByPrimaryKey(nextId);
				result.put("title", nextQuestion.getTitle());
				result.put("type", nextQuestion.getType()+"");
				result.put("id", nextQuestion.getId()+"");
				result.put("phone", phone);
				result.put("isEnd", "0");
			}
		}
		
		return result;
	}

    
}
