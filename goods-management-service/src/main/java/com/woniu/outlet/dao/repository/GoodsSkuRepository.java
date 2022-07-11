package com.woniu.outlet.dao.repository;

import com.alibaba.nacos.common.utils.StringUtils;
import com.woniu.outlet.dao.mysql.po.GoodsSku;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface GoodsSkuRepository extends ElasticsearchRepository<GoodsSku, Long> {

    default Page<GoodsSku> listGoodsSkuDtoByItem(String saleable, String title, String startTime, String endTime, Integer pageno, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageno - 1, pageSize);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withPageable(pageRequest);
        if (StringUtils.isBlank(saleable) && StringUtils.equals(saleable, "") &&
            StringUtils.isBlank(title) && StringUtils.equals(title, "") &&
            StringUtils.isBlank(startTime) && StringUtils.equals(startTime, "") &&
            StringUtils.isBlank(endTime) && StringUtils.equals(endTime, "")) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (StringUtils.isNotBlank(saleable) && !StringUtils.equals(saleable, ""))
                boolQueryBuilder.must(QueryBuilders.matchQuery("saleable", saleable));
            if (StringUtils.isNotBlank(title) && !StringUtils.equals(title, ""))
                boolQueryBuilder.must(QueryBuilders.matchQuery("title", title));
            if (StringUtils.isNotBlank(startTime) && !StringUtils.equals(startTime, ""))
                boolQueryBuilder.must(QueryBuilders.rangeQuery("create_time").gte(startTime));
            if (StringUtils.isNotBlank(endTime) && !StringUtils.equals(endTime, ""))
                boolQueryBuilder.must(QueryBuilders.rangeQuery("create_time").lte(endTime));
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        Page<GoodsSku> goodsSkuPage = this.search(nativeSearchQueryBuilder.build());
        return goodsSkuPage;
    }

}
