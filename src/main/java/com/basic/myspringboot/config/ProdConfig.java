package com.basic.myspringboot.config;

import com.basic.myspringboot.config.vo.CustomerVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdConfig {
    @Bean
    public CustomerVO customer() {
        return CustomerVO.builder() //CustomerVOBuilder
                .id(200L)
                .mode("운영환경")
                .build();
    }
}
