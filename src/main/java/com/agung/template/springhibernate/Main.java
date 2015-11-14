package com.agung.template.springhibernate;

import com.agung.template.springhibernate.service.MateriService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class Main {
    private static AbstractApplicationContext applicationContext;
    private static MateriService materiService;
    
    public static void initConfig(){
        applicationContext = new ClassPathXmlApplicationContext("classpath*:/config/ApplicationConfig.xml");
        materiService = (MateriService) applicationContext.getBean("materiService");
    }
     
    public static void main(String[] args) {
        initConfig();
       
    }
}
