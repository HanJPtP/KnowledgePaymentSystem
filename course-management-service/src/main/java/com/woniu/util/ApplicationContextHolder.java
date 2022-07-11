package com.woniu.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * 因为 ApplicationContextHolder 是 Spring IoC 容器创建的单例对象，且没有无参构造，
     * 所以，Spring IoC 容器就一定会调用下面的这个有参构造来创建 ApplicationContextHolder 单例对象。
     * 所有，根据其参数要求，Spring IoC 容器在调用有参构造是，会把 IoC 容器的 ApplicationContext 对象传进来。
     * 这样，ApplicationContextHolder 单例对象就【持有】了 ApplicationContext 对象，即，IoC 容器。

    public ApplicationContextHolder(ApplicationContext applicationContext) {
        APPLICATION_CONTEXT = applicationContext;
    }
    */

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * 因为 ApplicationContextHolder 是 Spring IoC 容器创建的单例对象，
     * Spring IoC 容器会来创建它，在创建完之后，Spring 会发现这个类实现了 ApplicationContextAware 接口，
     * 然后，Spring 会调用这个 holder 对象 setApplicationContext 方法，并把代表 Spring IoC 容器的 ApplicationContext 对象传进来。
     * 这样，ApplicationContextHolder 单例对象就【持有】了 ApplicationContext 对象，即，IoC 容器。
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }

}
