package com.cb.gateway.entity;

import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;

/**
 * @author sxk
 * @date 2020/12/3 3:13 下午
 */
public class RecorderServerHttpRequestDecorator extends ServerHttpRequestDecorator {

  private final List<DataBuffer> dataBuffers = new ArrayList<>();

  public RecorderServerHttpRequestDecorator(ServerHttpRequest delegate) {
    super(delegate);
    super.getBody().map(dataBuffer -> {
      dataBuffers.add(dataBuffer);
      return dataBuffer;
    }).subscribe();
  }

  @Override
  public Flux<DataBuffer> getBody() {
    return copy();
  }

  private Flux<DataBuffer> copy() {
    return Flux.fromIterable(dataBuffers)
        .map(buf -> buf.factory().wrap(buf.asByteBuffer()));
  }
}
