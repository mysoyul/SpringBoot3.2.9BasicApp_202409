package com.basic.myspringboot.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {
    public void run(ApplicationArguments args) throws Exception {
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