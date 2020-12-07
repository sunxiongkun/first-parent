package com.cb.order.web.controller;

import com.cb.order.entity.OrderEntity;
import com.cb.order.service.OrderService;
import com.cb.user.entity.UserEntity;
import com.cb.user.service.TokenService;
import com.cb.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sxk
 * @date 2020/12/2 6:04 下午
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private OrderService orderService;
  @Autowired
  private UserService userService;
  @Autowired
  private TokenService tokenService;

  @GetMapping("/get")
  public OrderEntity getOrderInfo(Integer id) {
    log.info("get order info :{}", id);
    final String token = tokenService.getToken(id);
    log.info("token:{}", token);
    final UserEntity userInfo = userService.getUserInfo(id);
    log.info("get userInfo :{}", userInfo);
    return orderService.getOrderInfo(id);
  }

  @PostMapping("/save")
  public Integer saveOrder(OrderEntity orderEntity) {
    return orderService.saveOrder(orderEntity);
  }

}
