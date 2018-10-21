package com.muteng.dgjs.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * date: 2018/10/21 11:08
 * author: snowxuyu
 * describe:
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -3088104317098344394L;
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        if (StringUtils.isBlank(message)) {
            message = super.getMessage();
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public BusinessException() {
        super("unknown");
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BusinessException(String code,  String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(String code,  String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }


    public BusinessException(Throwable cause) {
        super("unknown", cause);
    }
}
