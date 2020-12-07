package com.cb.common.enmus;


import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author sxk
 * @date 2020/12/4 1:48 下午
 */
@Getter
public enum SysMsgEnum {
  // 200
  OK(HttpStatus.OK.value(), "OK"),
  // 500
  ERROR_SERVICE_INNER(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR_SERVICE_INNER"),
  // 400
  ERROR_PARAM(HttpStatus.BAD_REQUEST.value(), "ERROR_PARAM"),
  // 401
  ERROR_NO_AUTH(HttpStatus.UNAUTHORIZED.value(), "ERROR_NO_AUTH");

  private final int code;
  private final String msg;

  SysMsgEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }


}
