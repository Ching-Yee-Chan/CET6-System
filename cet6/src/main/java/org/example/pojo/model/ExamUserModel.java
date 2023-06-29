package org.example.pojo.model;

import org.example.common.enums.EnumEntity;
import org.example.pojo.entity.ExamUser;
import lombok.Data;

/**
 * (ExamUser)实体类
 *
 * @author makejava
 * @since 2023-06-25 10:23:49
 */
@Data
public class ExamUserModel extends ExamUser {
    private static final long serialVersionUID = 574507876925790470L;


    /**
     * 状态：0未报名，1已报名，未支付，2报名已支付，3取消报名
     */
    private EnumEntity status;

    private ExamModel examModel;

    public ExamModel getExamModel() {
        return examModel;
    }

    public void setExamModel(ExamModel examModel) {
        this.examModel = examModel;
    }
}

