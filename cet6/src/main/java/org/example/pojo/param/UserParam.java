package org.example.pojo.param;

import org.example.common.enums.impl.UserRoleEnum;
import org.example.pojo.entity.User;
import lombok.Data;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2023-06-28 23:48:02
 */
@Data
public class UserParam extends User {
    private static final long serialVersionUID = 247229862520241272L;

    /**
     * 角色：1普通用户，2管理员
     */
    private UserRoleEnum role;
    private String inputCode;


}

