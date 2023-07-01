package com.exam.common.enums.impl;

import com.exam.common.enums.EnumCode;

public enum ExamTypeEnum implements EnumCode {

    Mandarin(1, "英语六级考试"),
    Cet_Band_4(2, "英语四级考试"),
    Cet_Band_6(3, "英语六级口语考试"),
    Accounting_Exam(4, "英语四级口语考试"),
    Computer_Rank_Examination(5, "德语六级考试"),
    Teacher_Qualification_Examination(6, "德语六级口语考试"),
    Test_Of_Spoken_English(7, "俄语六级考试"),
    Other_Test(8, "俄语六级口语考试"),

    ;

    private final long code;
    private final String msg;

    ExamTypeEnum(long code, String msg) {
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

    public static ExamTypeEnum returnValue(String str) {
        ExamTypeEnum[] values = values();
        for (ExamTypeEnum value : values) {
            if (value.toString().equals(str) ) {
                return value;
            }
        }
        return Mandarin;
    }
}
