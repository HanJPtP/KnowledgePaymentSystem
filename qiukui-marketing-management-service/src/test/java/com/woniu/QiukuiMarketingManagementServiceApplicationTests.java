package com.woniu;

import com.woniu.outlet.dao.mysql.pojo.QkMarketingDetails;
import com.woniu.outlet.dao.mysql.pojo.QkMarketingStatus;
import com.woniu.service.impl.QkMarketingStatusServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class QiukuiMarketingManagementServiceApplicationTests {
    @Test
    void contextLoads() {

        QkMarketingDetails qkMarketingDetails = QkMarketingDetails.builder()
                .id(1L)
                .activityName("aa")
                .marketingTypeId(1L)
                .activityTime(1)
                .memberShip("1").build();

        System.out.println(qkMarketingDetails);

    }

}
