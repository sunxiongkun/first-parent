package com.cb.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sxk
 * @date 2020/12/3 11:05 上午
 */
@FeignClient(value = "user-center",contextId = "TokenService", path = "/token")
public interface TokenService {

  @GetMapping("/get")
  String getToken(@RequestParam(name = "id") Integer id);
}
