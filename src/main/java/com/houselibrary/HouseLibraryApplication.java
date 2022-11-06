package com.houselibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.Column;

@SpringBootApplication
@ComponentScan({"com.houselibrary.api", "com.houselibrary.core"})
public class HouseLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseLibraryApplication.class, args);
    }

}
