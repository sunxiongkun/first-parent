package com.cb.user.web.controller;

import com.cb.user.entity.UserEntity;
import com.cb.user.service.TokenService;
import com.cb.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sxk
 * @date 2020/12/3 11:07 上午
 */
@Slf4j
@RestController
@RequestMapping("/token")
public class TokenController {

  @Autowired
  private UserService userService;
  @Autowired
  private TokenService tokenService;

  @GetMapping("/get")
  public String getToken(@RequestParam(name = "id") Integer id) {
    UserEntity user = userService.getUserInfo(id);
    return tokenService.getToken(user.getId());
  }
}
