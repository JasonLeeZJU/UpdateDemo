/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatepluginlib.impl;

/**
 * 内部提供的一个异常。用于标识及提供在网络操作中出现的异常。
 * @author JasonLee
 */
public class HttpException extends RuntimeException {

    private int code;
    private String errorMsg;

    public HttpException (int code,String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
