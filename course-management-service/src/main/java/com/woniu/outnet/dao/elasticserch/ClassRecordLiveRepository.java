package com.woniu.outnet.dao.elasticserch;

import com.woniu.outnet.dao.pojo.ClassRecordLive;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;

public interface ClassRecordLiveRepository extends ElasticsearchRepository<ClassRecordLive,Long> {


    @Query("{\n" +
            "    \"bool\":{\n" +
            "      \"must\":[\n" +
            "        {\"query_string\":{\"query\":\"?0\",\"fields\":[\"name\"]}\n" +
            "        },\n" +
            "        {\"range\":{\n" +
            "          \"estarttime\":{\n" +
            "            \"from\":?1,\"to\":?2,\"include_lower\" : true, \"include_upper\":true\n" +
            "                        }\n" +
            "                  }\n" +
            "        }\n" +
            "      ]\n" +
            "    }}")
    Page<ClassRecordLive> getClassRecordLiveByNameAndTimeBetween(String name, String fromTime, String toTime, Pageable pageable);


    Page<ClassRecordLive> findByNameAndEstarttimeBetween(String name,Date fromTime,Date toTime,Pageable pageable);

    @Query("{\n" +
            "  \"bool\":{\n" +
            "      \"must\":[{\n" +
            "        \"query_string\":{\n" +
            "          \"query\":\"?0\",\"fields\":[\"name\"]\n" +
            "        }\n" +
            "        },\n" +
            "        {\"range\":{\n" +
            "          \"estarttime\":{\n" +
            "            \"from\":\"?1\",\"to\":null,\"include_lower\" : true, \"include_upper\":true\n" +
            "                        }\n" +
            "                  }\n" +
            "        },\n" +
            "        {\"range\":{\n" +
            "          \"estarttime\":{\n" +
            "            \"from\":null,\"to\":\"?2\",\"include_lower\" : true, \"include_upper\":true\n" +
            "                        }\n" +
            "                  }\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }")
    Page<ClassRecordLive> findByChoose(String name,String fromTime,String toTime,Pageable pageable);


   default Page<ClassRecordLive> findByPage(String name,String fromTime,String toTime,Pageable pageable){

       QueryBuilder queryName = null;
       if(name.length()>0){
           queryName = QueryBuilders.wildcardQuery("name", name);
       }
       QueryBuilder queryFromTime=null;
       if(fromTime.length()>0){
           queryFromTime= QueryBuilders.rangeQuery("estarttime").gte(fromTime);
       }
       QueryBuilder queryToTime = null;
       if(toTime.length()>0){
           queryToTime = QueryBuilders.rangeQuery("estarttime").lte(toTime);
       }
       QueryBuilder all = QueryBuilders.boolQuery().must(queryName).must(queryFromTime).must(queryToTime);
       return this.search(all,pageable);
    }


    default Page<ClassRecordLive> findByPage2(String name,String fromTime,String toTime,Pageable pageable){

        QueryBuilder queryName = QueryBuilders.wildcardQuery("name", name);

        QueryBuilder queryFromTime = QueryBuilders.rangeQuery("estarttime").gte(fromTime);

        QueryBuilder queryToTime = QueryBuilders.rangeQuery("estarttime").lte(toTime);
        QueryBuilder all = QueryBuilders.boolQuery().must(queryName).must(queryFromTime).must(queryToTime);
        return this.search(all,pageable);
    }
}
