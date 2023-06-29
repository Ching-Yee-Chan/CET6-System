package org.example.pojo.model;

import org.example.common.enums.EnumEntity;
import org.example.pojo.entity.Exam;
import lombok.Data;

/**
 * (Exam)实体类
 *
 * @author Bofan
 * @since 2023-06-26 16:45:23
 */
@Data
public class ExamModel extends Exam {
    private static final long serialVersionUID = -67783677645813997L;


    /**
     * 状态:0未开始，1报名中，2结束报名，
     */
    private EnumEntity status;

    /**
     * 类型
     */
    private EnumEntity examType;

    private String examUserStatus;




}

