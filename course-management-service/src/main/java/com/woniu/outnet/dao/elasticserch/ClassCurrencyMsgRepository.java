package com.woniu.outnet.dao.elasticserch;

import com.woniu.outnet.dao.pojo.ClassCurrencyMsg;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ClassCurrencyMsgRepository extends ElasticsearchRepository<ClassCurrencyMsg,Long> {

    default Page<ClassCurrencyMsg> fingByPage(String name, Double startPrcie, Double endPrice, String starttiem, String endtime, Pageable pageable){
        QueryBuilder queryName = QueryBuilders.wildcardQuery("name", name);
        QueryBuilder queryStartPrice = QueryBuilders.rangeQuery("privce").gte(startPrcie);
        QueryBuilder queryEndPrice =   QueryBuilders.rangeQuery("privce").lte(endPrice);
        QueryBuilder queryStartTime = QueryBuilders.rangeQuery("groundingtime").gte(starttiem);
        QueryBuilder queryEndTime =   QueryBuilders.rangeQuery("groundingtime").lte(endtime);
        QueryBuilder all = QueryBuilders.boolQuery().must(queryName).must(queryEndPrice).must(queryEndTime).must(queryStartPrice).must(queryStartTime);
        return this.search(all,pageable);
    }

}
