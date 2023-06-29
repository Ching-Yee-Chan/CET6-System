package org.example.pojo.model;

import org.example.common.enums.EnumEntity;
import org.example.pojo.entity.ExamIntroduction;
import lombok.Data;

/**
 * (ExamIntroduction)实体类
 *
 * @author makejava
 * @since 2023-06-29 10:45:20
 */
@Data
public class ExamIntroductionModel extends ExamIntroduction {
    private static final long serialVersionUID = 993859514398744435L;


    /**
     * 类型
     */
    private EnumEntity examType;




}

