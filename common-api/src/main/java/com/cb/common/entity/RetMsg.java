package com.cb.common.entity;

import com.cb.common.enmus.SysMsgEnum;
import com.cb.common.exception.BusinessException;
import java.text.MessageFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

/**
 * @author sxk
 * @date 2020/12/4 1:54 下午
 */
@Data
@Builder
public class RetMsg<T> {

  private Integer status;
  private String msg;
  private Integer num;
  private T data;

  public static <T> RetMsg<T> successData(T data) {
    return RetMsg.<T>builder()
        .status(SysMsgEnum.OK.getCode())
        .data(data)
        .build();
  }

  public static <T> RetMsg<T> successData(T data, Integer num) {
    return RetMsg.<T>builder()
        .status(SysMsgEnum.OK.getCode())
        .num(num)
        .data(data)
        .build();
  }

  public static <T> RetMsg<T> errorMsg(BusinessException e) {
    SysMsgEnum sysMsgEnum = e.getSysMsgEnum();
    return errorMsg(sysMsgEnum, e.getSysMsgParam());
  }

  public static <T> RetMsg<T> errorMsg(SysMsgEnum sysMsgEnum) {
    return errorMsg(sysMsgEnum, null);
  }

  public static <T> RetMsg<T> errorMsg(SysMsgEnum sysMsgEnum, Object[] sysMsgParam) {
    return errorMsg(sysMsgEnum, sysMsgParam, null);
  }

  public static <T> RetMsg<T> errorMsg(SysMsgEnum sysMsgEnum, Object[] sysMsgParam,
      @Nullable T data) {
    String msg;
    if (sysMsgParam == null || sysMsgParam.length == 0) {
      msg = sysMsgEnum.getMsg();
    } else {
      msg = MessageFormat.format(sysMsgEnum.getMsg(), sysMsgParam);
    }
    return RetMsg.<T>builder()
        .status(sysMsgEnum.getCode())
        .msg(msg)
        .data(data)
        .build();
  }

}
