package com.leyou.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.leyou.service.dao")
public class LeyouServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeyouServiceProviderApplication.class, args);
    }

}
