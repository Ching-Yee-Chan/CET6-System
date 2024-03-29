package org.example.common.enums.impl;

import org.example.common.enums.EnumCode;

public enum ExamUserStatusEnum implements EnumCode {

    NoApply(0,"未报名"),
    Apply_NoPay (1,"报名未支付"),
    Apply_Pay (2,"报名已支付"),
    Canal(3,"取消报名"),
    NoAnswer(4, "考试未完成"),
    FinishAnswer(5, "考试完成")
    ;
    private final long code;
    private final String msg;

    ExamUserStatusEnum(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
