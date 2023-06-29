package org.example.common.enums;

import lombok.Data;

/**
 * 枚举转换成的实体类
 *
 * @author Jiankun
 * @since 2023-06-29 00:54:34
 */
@Data
public class EnumEntity implements EnumCode {
    private EnumCode enumCode;
    private long code;
    private String msg;

}
