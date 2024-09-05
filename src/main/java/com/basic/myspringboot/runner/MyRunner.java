package com.basic.myspringboot.runner;

import com.basic.myspringboot.config.MyBootProperties;
import com.basic.myspringboot.config.vo.CustomerVO;
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

    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Profiles CustomerVO Mode = " + customer.getMode());
        System.out.println("MyBootProperties getFullName = " + properties.getFullName());
        System.out.println("MyBootProperties getAge = " + properties.getAge());

        System.out.println("${myboot.name} = " + name);
        System.out.println("${myboot.age} = " + age);
        System.out.println("${myboot.fullName} = " + environment.getProperty("myboot.fullName"));

        System.out.println("Program Arguments foo : " + args.containsOption("foo"));
        System.out.println("VM Arguments bar : " + args.containsOption("bar"));


        args.getOptionNames() //Set<String>
                //forEach(Consumer) void accept(T t)
                .forEach(name -> System.out.println("아규먼트 Name = " + name));
                //.forEach(System.out::println);

        if(args.containsOption("spring.application.name")){
            args.getOptionValues("spring.application.name")
                    .forEach(value -> System.out.println("spring.application.name Value = " + value));
        }

    }
}