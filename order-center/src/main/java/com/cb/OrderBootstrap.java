package com.cb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author sxk
 * @date 2020/12/2 6:08 下午
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cb.user"})
public class OrderBootstrap implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(OrderBootstrap.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

  }
}
