package com.cb.common.exception;

import com.cb.common.enmus.SysMsgEnum;
import com.cb.common.entity.RetMsg;
import com.cb.common.service.LocaleMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author sxk
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @Autowired
  protected LocaleMessageService localeMessageService;

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public RetMsg<String> exceptionHandler(Exception e) {
    BusinessException be = new BusinessException(
        SysMsgEnum.ERROR_SERVICE_INNER, e);
    logException(be);
    return RetMsg.errorMsg(be);
  }


  private void logException(Exception e) {
    log.error("global error:", e);
  }
}
