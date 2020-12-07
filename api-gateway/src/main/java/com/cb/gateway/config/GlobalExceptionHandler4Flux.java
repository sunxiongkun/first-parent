package com.cb.gateway.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author sxk
 * @date 2020/12/3 5:50 下午
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler4Flux implements ErrorWebExceptionHandler {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
    log.error("gateway global error:", ex);
    ServerHttpResponse response = exchange.getResponse();

    if (response.isCommitted()) {
      return Mono.error(ex);
    }
    // header set_json响应
    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
    //是否响应状态异常
    if (ex instanceof ResponseStatusException) {
      response.setStatusCode(((ResponseStatusException) ex).getStatus());
    }

    return response
        .writeWith(Mono.fromSupplier(() -> {
          DataBufferFactory bufferFactory = response.bufferFactory();
          try {
            //返回json异常原因给前端
            return bufferFactory.wrap(objectMapper.writeValueAsBytes("请稍后重试"));
          } catch (JsonProcessingException e) {
            log.warn("Error writing response", ex);
            return bufferFactory.wrap(new byte[0]);
          }
        }));
  }

}

