package com.cb.common.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author sxk
 * @date 2020/12/3 3:14 下午
 * @description 用来打印日志，字段尽量小
 */
@Data
@Builder
public class LogEntity {

  private String ip;
  /**
   * startTime
   */
  private String st;
  private String url;
  private String method;
  /**
   * requestId
   */
  private String reqId;
  /**
   * 请求
   */
  private String req;
  /**
   * 返回
   */
  private Object res;
  private int status;
  /**
   * runtime
   */
  private long rt;
}
