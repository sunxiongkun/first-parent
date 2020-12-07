package com.cb.common.service;

import java.util.Locale;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocaleMessageService {

  @Resource
  private MessageSource messageSource;

  public String getMessage(String key) {
    try {
      Locale locale = LocaleContextHolder.getLocale();
      return messageSource.getMessage(key, null, locale);
    } catch (NoSuchMessageException e) {
      log.warn("key: {} message not exists", key);
    }
    return key;
  }

  public String getMessage(String key, Object[] params) {
    try {
      Locale locale = LocaleContextHolder.getLocale();
      return messageSource.getMessage(key, params, locale);
    } catch (NoSuchMessageException e) {
      log.warn("key: {} message not exists", key);
    }
    return key;
  }

}
