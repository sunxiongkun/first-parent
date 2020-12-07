package com.cb.order.service;

import com.cb.order.entity.OrderEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sxk
 * @date 2020/12/2 4:30 下午
 */
@FeignClient(value = "order-center", contextId = "OrderService", path = "/order")
public interface OrderService {

  @GetMapping("/get")
  OrderEntity getOrderInfo(@RequestParam(name = "id") Integer id);

  @PostMapping("/save")
  Integer saveOrder(OrderEntity orderEntity);

}
