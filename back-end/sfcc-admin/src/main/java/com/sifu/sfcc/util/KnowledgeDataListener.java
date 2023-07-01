package com.sifu.sfcc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.sifu.sfcc.dao.CcKnowledgeDao;
import com.sifu.sfcc.model.CcKnowledge;

@Service
public class KnowledgeDataListener extends AnalysisEventListener<CcKnowledge> {
    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    /**
     * 缓存的数据
     */
    private List<CcKnowledge> list = new ArrayList<>(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    @Autowired
    private CcKnowledgeDao knowledgeDao;

    public KnowledgeDataListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
//    	knowledgeDao = new CcKnowledgeDao();
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param demoDAO
     */
    public KnowledgeDataListener(CcKnowledgeDao knowledgeDao) {
        this.knowledgeDao = knowledgeDao;
    }
    
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    	LOGGER.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    	//{0:"编号",1:"问题",2:"答案"}
        // 根据自己的情况去做表头的判断即可
    	if(!"{0:\"编号\",1:\"问题\",2:\"答案\"}".equals(JSON.toJSONString(headMap))) {
    		throw new NumberFormatException();
    	}
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(CcKnowledge data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        knowledgeDao.batchInsert(list);
        list = new ArrayList<>(BATCH_COUNT);
//        knowledgeMapper.
        LOGGER.info("存储数据库成功！");
    }
}
