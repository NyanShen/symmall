package com.sym.common.enums;

import com.sym.common.constants.HttpStatus;

public enum ResultCode {

    // 200
    SUCCESS(HttpStatus.SUCCESS, "操作成功"),
    // 400
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "请求参数错误"),
    // 401
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "用户未登录"),
    // 403
    FORBIDDEN(HttpStatus.FORBIDDEN, "用户无权限"),
    // 404
    NOT_FOUND(HttpStatus.NOT_FOUND, "请求资源不存在"),
    // 405
    METHOD_NOT_ALLOWED(HttpStatus.BAD_METHOD, "请求方法不允许"),

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.ERROR, "服务器错误"),
    // 502
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "网关错误"),
    // 503
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "服务不可用"),
    // 504
    GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "网关超时"),

    // 业务异常
    BUSINESS_ERROR(HttpStatus.ERROR, "业务异常");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
