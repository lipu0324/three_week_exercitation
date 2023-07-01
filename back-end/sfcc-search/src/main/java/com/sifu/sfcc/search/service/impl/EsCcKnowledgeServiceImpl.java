package com.sifu.sfcc.search.service.impl;

import com.sifu.sfcc.search.dao.EsCcKnowledgeDao;
import com.sifu.sfcc.search.domain.CcKnowledge;
import com.sifu.sfcc.search.repository.EsCcKnowledgeRepository;
import com.sifu.sfcc.search.service.EsCcKnowledgeService;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class EsCcKnowledgeServiceImpl implements EsCcKnowledgeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsCcKnowledgeServiceImpl.class);
    @Autowired
    private EsCcKnowledgeRepository esCcKnowledgeRepository;

    @Autowired
    EsCcKnowledgeDao ccKnowledgeDao;

    @Override
    public int importAll() {
        List<CcKnowledge> ccKnowledges = ccKnowledgeDao.getAllEsCcKnowledgeList();
        Iterable<CcKnowledge> ccKnowledgeIterable = esCcKnowledgeRepository.saveAll(ccKnowledges);
        Iterator<CcKnowledge> ccKnowledgeIterator = ccKnowledgeIterable.iterator();
        int result = 0;
        while(ccKnowledgeIterator.hasNext()){
            result ++;
            ccKnowledgeIterator.next();
        }
        return result;
    }

    @Override
    public Page<CcKnowledge> search(String keyword , Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esCcKnowledgeRepository.findByAnswer(keyword , pageable);
    }

    @Override
    public Page<CcKnowledge> search(String keyword,Integer pageNum, Integer pageSize,Integer sort) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        //搜索
        if (StringUtils.isEmpty(keyword)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            //过滤
            //在 Es 中function_score是用于处理文档分值的 DSL，它会在查询结束后对每一个匹配的文档进行一系列的重打分操作，最后以生成的最终分数进行排序。
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("answer", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));

            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        if (sort == 0){
            //按发布时间由新到旧
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        }
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
        return esCcKnowledgeRepository.search(searchQuery);
    }

}
