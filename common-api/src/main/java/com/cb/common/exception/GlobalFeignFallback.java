package com.cb.common.exception;

import com.cb.common.enmus.SysMsgEnum;
import com.cb.common.entity.RetMsg;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author sxk
 * @date 2020/12/4 6:10 下午
 */
@Slf4j
@Component
public class GlobalFeignFallback implements FallbackFactory<RetMsg<String>> {

  @Override
  public RetMsg<String> create(Throwable e) {
    //打印服务端接口的异常信息
    BusinessException be = new BusinessException(
        SysMsgEnum.ERROR_SERVICE_INNER, e);
    log.error("feign error:", be);
    return RetMsg.errorMsg(be);
  }
}