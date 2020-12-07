package com.cb.common.exception;

import com.cb.common.enmus.SysMsgEnum;
import lombok.Data;

/**
 * @author sxk
 */
@Data
public class BusinessException extends RuntimeException {

  private SysMsgEnum sysMsgEnum;
  private Object[] sysMsgParam;

  public BusinessException(SysMsgEnum sysMsgEnum, Throwable cause) {
    super(sysMsgEnum.getMsg(), cause);
    this.sysMsgEnum = sysMsgEnum;
  }

  public BusinessException(SysMsgEnum sysMsgEnum, Object[] sysMsgParam, Throwable cause) {
    super(sysMsgEnum.getMsg(), cause);
    this.sysMsgEnum = sysMsgEnum;
    this.sysMsgParam = sysMsgParam;
  }

}
