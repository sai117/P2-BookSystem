package com.tuny.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * 统一异常拦截处理
 *
 * @author wangzhaofeng
 * @date 2019-04-09 11:14:15
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    /**
     * 拦截Exception类的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class, ParamCheckException.class})
    @ResponseBody
    public ApiResult checkExceptionHandler(Exception e) {
        // 接口参数的数据验证异常的处理
        String msg = e.getMessage();
        // 处理多个数据验证，默认的验证信息提示，带有vo的类名和字段名，需要去掉，并且一次只返回一个错误验证
        if (StringUtils.isEmpty(msg)) {
            msg = getMessage(e.getCause());
        }
        String[] msgFirstArr = msg.split(",")[0].split(":");
        if (msgFirstArr.length > 1) {
            msg = msgFirstArr[1].trim();
        }
        return ApiResult.createError(msg);
    }

    @ExceptionHandler(Exception.class)
    public ApiResult exceptionHandler(Exception e){
        // todo 临时返回所有错误信息，正式发布需要屏蔽
        String msg = e.getMessage();
        // 处理多个数据验证，默认的验证信息提示，带有vo的类名和字段名，需要去掉，并且一次只返回一个错误验证
        if (StringUtils.isEmpty(msg)) {
            msg = getMessage(e.getCause());
        }

        //打印异常输出
        log.error("系统异常", e);
        return ApiResult.createError(msg);
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public void exceptionHandler(Exception e){
//        //打印异常输出
//        log.error("系统异常", e);
//    }

    /**
     * 递归查找错误描述
     *
     * @param cause
     * @return
     */
    private String getMessage(Throwable cause) {
        if (cause == null) {
            return "";
        }
        if (StringUtils.isEmpty(cause.getMessage())) {
            // 递归查找错误message
            return getMessage(cause.getCause());
        } else {
            return cause.getMessage();
        }
    }
}
