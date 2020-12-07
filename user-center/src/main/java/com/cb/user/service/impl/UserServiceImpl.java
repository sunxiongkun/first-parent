package com.cb.user.service.impl;

import com.cb.user.entity.UserEntity;
import com.cb.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author sxk
 * @date 2020/12/2 4:32 下午
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Override
  public UserEntity getUserInfo(Integer id) {
    UserEntity user = new UserEntity();
    user.setId(id);
    log.info("get user info:{}", user);
    return user;
  }

  @Override
  public Integer saveUser(UserEntity userEntity) {
    log.info("saveUser:{}", userEntity);
    return userEntity.getId();
  }
}
