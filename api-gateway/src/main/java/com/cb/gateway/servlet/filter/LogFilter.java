package com.cb.gateway.servlet.filter;

import com.alibaba.fastjson.JSON;
import com.cb.common.entity.LogEntity;
import com.cb.gateway.entity.RecorderServerHttpRequestDecorator;
import com.cb.utils.LocalDateUtils;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author sxk
 * @date 2020/12/3 2:56 下午
 */
@Slf4j
@Component
public class LogFilter implements GlobalFilter, Ordered {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    final long startTime = System.currentTimeMillis();
    try {
      String now = LocalDateUtils.getNowTimeStr();
      ServerHttpRequest request = exchange.getRequest();
      RecorderServerHttpRequestDecorator requestDecorator = new RecorderServerHttpRequestDecorator(
          request);
      InetSocketAddress remoteAddress = requestDecorator.getRemoteAddress();
      HttpMethod method = requestDecorator.getMethod();
      URI url = requestDecorator.getURI();
      //读取requestBody传参
      final LogEntity logMsg = LogEntity
          .builder()
          .ip(Optional.ofNullable(remoteAddress).map(InetSocketAddress::getHostName).orElse(null))
          .st(now)
          .url(String.valueOf(url))
          .method(String.valueOf(method)).build();

      Flux<DataBuffer> body = requestDecorator.getBody();
      body.subscribe(buffer -> {
        CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
        logMsg.setReq(charBuffer.toString());
      });

      ServerHttpResponse response = exchange.getResponse();

      DataBufferFactory bufferFactory = response.bufferFactory();

      ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
        @Override
        public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
          if (body instanceof Flux) {
            Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
            return super.writeWith(fluxBody.map(dataBuffer -> {
              // probably should reuse buffers
              byte[] content = new byte[dataBuffer.readableByteCount()];
              dataBuffer.read(content);
              String responseResult = new String(content, StandardCharsets.UTF_8);
              logMsg.setRes(responseResult);
              if (this.getStatusCode() != null) {
                logMsg.setStatus(this.getStatusCode().value());
              }
              logMsg.setRt(System.currentTimeMillis() - startTime);
              log.info("{}", JSON.toJSONString(logMsg));
              return bufferFactory.wrap(content);
            }));
          }
          return super.writeWith(body); // if body is not a flux. never got there.
        }
      };
      return chain
          .filter(exchange.mutate().request(requestDecorator).response(decoratedResponse).build());
    } catch (Exception e) {
      log.error("logFilter error:", e);
      return null;
    }
  }

  @Override
  public int getOrder() {
    return -2;
  }

}