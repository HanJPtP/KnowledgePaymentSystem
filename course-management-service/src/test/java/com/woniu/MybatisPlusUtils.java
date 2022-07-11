package com.woniu;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mybatis代码生成工具类
 */
public class MybatisPlusUtils {
    public static void main(String[] args) {
        try {
            generator("liuqi",//作者
                    "jdbc:mysql://192.172.0.205:3306/knowledge_class?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false",
                    "com.mysql.cj.jdbc.Driver",//数据库驱动
                    "root",//数据库帐号
                    "123456",//数据库密码
                    "com.woniu",//项目最大的包名
                    "course-management-service",//项目名
                    "class_appointment,class_classification,class_coupon_msg,class_currency_msg,class_likes_msg,class_live_watch,class_live_watch_totle,class_record_live,class_currency_watch");//要操作的表名,多个表名用逗号隔开
            System.out.println("mybaits代码生成成功");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Mybatis一键生成entity,mapper,mapper.xml,service,serviceImpl,controller
     * @param author            开发人员
     * @param url               驱动连接的URL
     * @param driverName        驱动名称
     * @param username          数据库连接用户名
     * @param password          数据库连接密码
     * @param parentPackage     父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     * @param projectModule     项目名
     * @param tableName         表名,多个表名逗号隔开
     */
    public static void generator(String author,
                                 String url,
                                 String driverName,
                                 String username,
                                 String password,
                                 String parentPackage,
                                 String projectModule,
                                 String tableName) {
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(globalConfig(author,projectModule));//全局配置
        mpg.setDataSource(dataSourceConfig(url,driverName,username,password));//数据源配置
        mpg.setPackageInfo(packageConfig(parentPackage));//包配置
        mpg.setStrategy(strategyConfig(tableName));//策略配置
        mpg.setTemplate(templateConfig());//模板配置
        mpg.execute();
    }

    /**
     * 全局配置
     * @param author            开发人员
     * @param projectModule     项目模块名
     * @return                  GlobalConfig
     */
    private static GlobalConfig globalConfig (String author, String projectModule) {
        String projectPath = System.getProperty("user.dir");
        GlobalConfig globalConfig = new GlobalConfig();
        // 文件输出目录
        globalConfig.setOutputDir(projectPath + "/" + projectModule + "/src/main/java");
        // 添加作者信息
        globalConfig.setAuthor(author);
        //设置时间类型为Date
        globalConfig.setDateType(DateType.TIME_PACK);
        // 生成文件后不默认打开
        globalConfig.setOpen(false);
        // 自定义service生成的名字，用于删除自动生成的I前缀
        //globalConfig.setServiceName("%sService");
        return globalConfig;
    }

    /**
     * 数据源设置
     * @param url           驱动连接的URL
     * @param driverName    驱动名称
     * @param username      数据库连接用户名
     * @param password      数据库连接密码
     * @return              DataSourceConfig
     */
    private static DataSourceConfig dataSourceConfig (String url,
                                                      String driverName,
                                                      String username,
                                                      String password) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDriverName(driverName);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        return dataSourceConfig;
    }

    /**
     * 包配置
     * @param parentPackage       父包名，最大的包名
     * @return                  PackageConfig
     */
    private static PackageConfig packageConfig(String parentPackage) {
        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        // 包名
        packageConfig.setParent(parentPackage);
        //各个包目录起名
        packageConfig.setEntity("outnet.dao.pojo");
        packageConfig.setMapper("outnet.dao.mysql");
        packageConfig.setXml("dao");
        packageConfig.setService("service");
        return packageConfig;
    }

    /**
     * 策略配置
     * @param tableName     数据库表名称，多个用英文逗号隔开
     * @return              StrategyConfig
     */
    private static StrategyConfig strategyConfig (String tableName) {
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 字段驼峰命名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // 设置实体类的lombok(此处看个人使用，如果不使用lombok，那么在生成之后再去添加构造方法等等)
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        // scanner("表名，多个英文逗号分割").split(",")
        strategyConfig.setInclude((tableName).split(","));
        // 驼峰生成方法
        strategyConfig.setControllerMappingHyphenStyle(true);
        return strategyConfig;
    }

    /**
     * 模板配置项
     * @return  TemplateConfig
     */
    private static TemplateConfig templateConfig () {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(ConstVal.TEMPLATE_XML);
        //不生成mapper.xml文件
        templateConfig.setXml(null);
        //不生成service
        templateConfig.setService(null);
        //不生成service实现类
        templateConfig.setServiceImpl(null);
        //不生成controller类
        templateConfig.setController(null);
        return templateConfig;
    }
}
