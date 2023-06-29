package org.example.pojo.param;

import org.example.common.enums.impl.InfoTypeEnum;
import org.example.pojo.entity.Info;
import lombok.Data;

/**
 * (Info)实体类
 *
 * @author makejava
 * @since 2023-06-11 11:13:06
 */
@Data
public class InfoParam extends Info {
    private static final long serialVersionUID = -29270330816255566L;


    /**
     * 类型：1报名时间公告，2考试时间公告，3成绩查询公告，4考试发布公告
     */
    private InfoTypeEnum type;

}

