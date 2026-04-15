package com.sym.common.exception;

import com.sym.common.constants.HttpStatus;
import com.sym.common.result.AjaxResult;
import com.sym.common.utils.type.SymStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 * 作用：全局捕获 SpringMVC DispatcherServlet 处理过程中抛出的所有异常，并统一返回 JSON 格式。
 * 请求 → DispatcherServlet
 *        → HandlerMapping 找到对应 Controller
 *        → HandlerAdapter 执行方法
 *            ↓
 * 【Controller 方法内抛出异常】
 *            ↓
 * DispatcherServlet 检测到异常
 *            ↓
 * 遍历所有 `@RestControllerAdvice/ExceptionHandler`
 *            ↓
 * 找到**最匹配**的异常处理方法
 *            ↓
 * 执行该方法，返回 JSON → 前端
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 业务异常处理
     */
    @ExceptionHandler(BusinessException.class)
    public AjaxResult handleBusinessException(BusinessException e) {
        return AjaxResult.error(e.getCode(), e.getMsg());
    }

    /**
     * @RequestBody 参数校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult handleValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String msg = SymStringUtils.isNull(fieldError) ? "参数错误" : fieldError.getDefaultMessage();
        log.warn("参数校验异常：{}", msg);
        return AjaxResult.error(HttpStatus.BAD_REQUEST, msg);
    }
    /**
     * 方法不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public AjaxResult handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        String msg = "不支持 " + e.getMethod() + " 请求方式";
        log.warn(msg);
        return AjaxResult.error(HttpStatus.BAD_METHOD, msg);
    }
    /**
     * 兜底异常（必须最后)
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e) {
        log.error("系统异常：{}", e.getMessage());
        return AjaxResult.error(HttpStatus.ERROR, "服务器繁忙，请稍后重试");
    }
}
