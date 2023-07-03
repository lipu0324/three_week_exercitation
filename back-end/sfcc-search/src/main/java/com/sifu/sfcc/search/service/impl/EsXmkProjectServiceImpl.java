package com.sifu.sfcc.search.service.impl;

import com.sifu.sfcc.search.dao.EsXmkProjectDao;
import com.sifu.sfcc.search.domain.XmkProject;
import com.sifu.sfcc.search.repository.EsXmkProjectRepository;
import com.sifu.sfcc.search.service.EsXmkProjectService;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
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
public class EsXmkProjectServiceImpl implements EsXmkProjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsXmkProjectServiceImpl.class);
    @Autowired
    private EsXmkProjectRepository esXmkProjectRepository;

    @Autowired
    EsXmkProjectDao xmkProjectDao;

    @Override
    public int importAll() {

        List<XmkProject> xmkProjectList = xmkProjectDao.getAllEsXmkProjectList();

        System.out.println("xmkProjectList = " + xmkProjectList);
        Iterable<XmkProject> xmkProjectIterable = esXmkProjectRepository.saveAll(xmkProjectList);
        System.out.println("xmkProjectIterable = " + xmkProjectIterable);
        Iterator<XmkProject> xmkProjectIterator = xmkProjectIterable.iterator();
        int result = 0;
        while(xmkProjectIterator.hasNext()){
            result ++;
            xmkProjectIterator.next();
        }
        return result;
    }

    @Override
    public Page<XmkProject> search(String keyword , Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        System.out.println("---- keyword = " + keyword);
        return esXmkProjectRepository.findBySXmNameOrSXmInfo(keyword , keyword, pageable);
    }

    @Override
    public Page<XmkProject> search(String keyword , Integer pageNum, Integer pageSize,Integer sort) {
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
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("sXmName", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("sXmInfo", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));

            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        if (sort == 0){
            //按发布时间由新到旧
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sPublishTime").order(SortOrder.DESC));
        }
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
        return esXmkProjectRepository.search(searchQuery);
    }
}
