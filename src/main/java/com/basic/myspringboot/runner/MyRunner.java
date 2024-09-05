package com.basic.myspringboot.runner;

import com.basic.myspringboot.config.MyBootProperties;
import com.basic.myspringboot.config.vo.CustomerVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private int age;

    @Autowired
    private Environment environment;

    @Autowired
    private MyBootProperties properties;

    @Autowired
    private CustomerVO customer;

    private final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Logger 구현 클래스명 = " + logger.getClass().getName());

        logger.info("Profiles CustomerVO Mode = {}", customer.getMode());
        logger.info("MyBootProperties getFullName = {}", properties.getFullName());
        logger.info("MyBootProperties getAge = {}", properties.getAge());

        logger.info("${myboot.name} = {}", name);
        logger.info("${myboot.age} = {}", age);
        logger.info("${myboot.fullName} = {}", environment.getProperty("myboot.fullName"));

        logger.debug("Program Arguments foo : " + args.containsOption("foo"));
        logger.debug("VM Arguments bar : " + args.containsOption("bar"));


        args.getOptionNames() //Set<String>
                //forEach(Consumer) void accept(T t)
                .forEach(name -> logger.info("아규먼트 Name = " + name));
                //.forEach(System.out::println);

        if(args.containsOption("spring.application.name")){
            args.getOptionValues("spring.application.name")
                    .forEach(value -> logger.debug("spring.application.name Value = " + value));
        }

    }
}