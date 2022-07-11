/**
 * FileName: OpenFeignConfig
 * Date:     2022/6/21 13:57
 * Author: YuanXQ
 * Description:
 */
package com.woniu.order.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/21 13:57
 * @since 1.0.0
 */
@Configuration
@EnableFeignClients(basePackages = {"com.woniu.order.outlet.client"})
public class OpenFeignConfig {

}