package com.cb.user.web.controller;

import com.alibaba.fastjson.JSON;
import com.cb.order.service.OrderService;
import com.cb.user.entity.UserEntity;
import com.cb.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sxk
 * @date 2020/12/2 4:36 下午
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private OrderService orderService;

  @GetMapping("/get")
  public UserEntity getUserInfo(Integer id) {
    log.info("get user info:{}", id);
    if (id == 10) {
      throw new NullPointerException();
    }
    return userService.getUserInfo(id);
  }

  @PostMapping("/save")
  public Integer saveUser(UserEntity userEntity) {
    log.info("save user :{}", JSON.toJSONString(userEntity));
    return userService.saveUser(userEntity);
  }
}
