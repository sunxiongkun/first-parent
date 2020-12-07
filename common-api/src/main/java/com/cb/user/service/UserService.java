package com.cb.user.service;

import com.cb.common.exception.GlobalFeignFallback;
import com.cb.user.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sxk
 * @date 2020/12/2 4:09 下午
 */
@FeignClient(value = "user-center", contextId = "UserService", path = "/user", fallbackFactory = GlobalFeignFallback.class)
public interface UserService {

  @GetMapping("/get")
  UserEntity getUserInfo(@RequestParam(name = "id") Integer id);

  @PostMapping("/save")
  Integer saveUser(UserEntity userEntity);

}
