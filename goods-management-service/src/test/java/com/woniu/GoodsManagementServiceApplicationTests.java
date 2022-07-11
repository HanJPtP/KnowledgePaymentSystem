package com.woniu;

import com.woniu.outlet.dao.dto.GoodsSkuDto;
import com.woniu.outlet.dao.mysql.po.GoodsSku;
import com.woniu.outlet.dao.repository.GoodsSkuRepository;
import com.woniu.outlet.mysql.mapper.GoodsSkuMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class GoodsManagementServiceApplicationTests {

    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    @Autowired
    private GoodsSkuRepository goodsSkuRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void demo1() {
        String str1 = "hello";
        String str2 = "he" + new String("llo");
        System.out.println("str1 == str2 ? : " + (str1 == str2));
        System.out.println("str1 eauals str2 ? : " + (str1.equals(str2)));

    }

    /**
     * 将 goodsskudto 信息存到 elasticsearch 中
     */
    @Test
    public void demo2() {
//        List<GoodsSkuDto> goodsSkuDtos = goodsSkuMapper.listAllGoodsSkuDto();
//        goodsSkuDtos.forEach(System.out::println);
//        goodsSkuRepository.saveAll(goodsSkuDtos);
    }

    @Test
    public void demo4() {
        List<GoodsSku> goodsSkuList = goodsSkuMapper.selectList(null);
        goodsSkuList.forEach(System.out::println);
        goodsSkuRepository.saveAll(goodsSkuList);
    }

    /**
     * elasticsearch 分页多条件查询 goodsskudto 信息
     */
    @Test
    public void demo3() {
        String description = "苹果";
        BigDecimal price = new BigDecimal(20.0);
        PageRequest pageRequest = PageRequest.of(0, 10);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withPageable(pageRequest);
        if (StringUtils.isBlank(description) && price == null) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (StringUtils.isNotBlank(description))
                boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("description", description));
            if (price != null)
                boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte(price));
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        Page<GoodsSku> page = goodsSkuRepository.search(nativeSearchQueryBuilder.build());
        System.out.println("显示分页信息");
        System.out.println(page);
        List<GoodsSku> goodsSkuDtos = page.getContent();
        goodsSkuDtos.forEach(System.out::println);
        System.out.println("显示结束");

//        Iterable<GoodsSkuDto> all = goodsSkuDtoRepository.findAll();
//        all.forEach(System.out::println);
    }

}
