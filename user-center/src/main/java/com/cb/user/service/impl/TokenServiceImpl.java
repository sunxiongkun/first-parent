package com.cb.user.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import com.cb.user.service.TokenService;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author sxk
 * @date 2020/12/3 11:20 上午
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

  @Override
  public String getToken(Integer id) {
    return Md5Utils.getMD5(String.valueOf(id), StandardCharsets.UTF_8.name());
  }
}
