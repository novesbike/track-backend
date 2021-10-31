package com.hexagonal.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class NovesBikeApplication {

  public static void main(String[] args) {
    SpringApplication.run(NovesBikeApplication.class, args);
  }

}
