package com.sym.common.exception;

import com.sym.common.enums.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final int code;
    private final String msg;

    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public BusinessException(String msg) {
        this.code = ResultCode.BUSINESS_ERROR.getCode();
        this.msg = msg;
    }

    public BusinessException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
