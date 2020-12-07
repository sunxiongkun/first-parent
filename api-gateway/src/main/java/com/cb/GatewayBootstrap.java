package com.cb;

import com.cb.user.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author sxk
 * @date 2020/12/2 8:25 下午
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cb.user"})
public class GatewayBootstrap implements CommandLineRunner {

  @Autowired
  private TokenService tokenService;

  public static void main(String[] args) {
    SpringApplication.run(GatewayBootstrap.class, args);
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    //@formatter:off
    return builder.routes()
        .route("user_route",r -> r.path("/user/get").uri("lb://user-center/user/get"))
        .route("token_route",r -> r.path("/token/get").uri("lb://user-center/token/get"))
        .route("order_route",r -> r.path("/order/get").uri("lb://order-center/order/get"))
        .route("web_socket_route", r -> r.path("/echo").uri("ws://localhost:9000"))
        .build();
    //@formatter:on
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("gateway token:{}", tokenService.getToken(100));
  }
}
