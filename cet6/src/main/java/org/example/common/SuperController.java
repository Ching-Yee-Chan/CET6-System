package org.example.common;


import org.example.common.enums.EnumCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller层的父类
 *
 * @author Jiankun
 * @since 2023-06-29 00:54:34
 */
public class SuperController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SuperController() {
    }

    protected <T> Result<T> success() {
        return Result.ok();
    }

    protected <T> Result<T> success(String msg) {
        return Result.ok(msg);
    }

    protected <T> Result<T> success(EnumCode enumCode) {
        return Result.ok(enumCode);
    }

    protected <T> Result<T> success(T data) {
        return Result.ok(data);
    }

    protected <T> Result<T> success(EnumCode enumCode, T data) {
        return Result.ok(enumCode, data);
    }

    protected <T> Result<T> failed() {
        return Result.failed();
    }

    protected <T> Result<T> failed(String msg) {
        return Result.failed(msg);
    }

    protected <T> Result<T> failed(EnumCode enumCode) {
        return Result.failed(enumCode);
    }

    protected <T> Result<T> failed(T data) {
        return Result.failed(data);
    }

    protected <T> Result<T> failed(EnumCode enumCode, T data) {
        return Result.failed(enumCode, data);
    }
}
