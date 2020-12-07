package com.cb.order.service.impl;

import com.cb.order.entity.OrderEntity;
import com.cb.order.service.OrderService;
import com.cb.user.entity.UserEntity;
import com.cb.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sxk
 * @date 2020/12/2 6:01 下午
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private UserService userService;

  @Override
  public OrderEntity getOrderInfo(Integer id) {
    OrderEntity order = new OrderEntity();
    order.setId(id);
    log.info("order:{}", order);
    UserEntity userInfo = userService.getUserInfo(id);
    log.info("user:{}", userInfo);
    return order;
  }

  @Override
  public Integer saveOrder(OrderEntity orderEntity) {
    return null;
  }
}
