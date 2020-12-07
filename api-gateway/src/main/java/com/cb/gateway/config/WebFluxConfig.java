package com.cb.gateway.config;

import java.util.stream.Collectors;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @author sxk
 * @date 2020/12/3 2:34 下午
 */
@Configuration
public class WebFluxConfig {

  @Bean
  @ConditionalOnMissingBean
  public HttpMessageConverters messageConverters(
      ObjectProvider<HttpMessageConverter<?>> converters) {
    return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
  }
}

