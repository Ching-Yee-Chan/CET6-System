package org.example.service;

import org.example.common.enums.impl.UserRoleEnum;
import org.example.pojo.param.UserParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void queryById() {
        userService.queryById("1497750318589411320");
    }

    @Test
    public void queryByPage() {
        UserParam userParam = new UserParam();
        userParam.setUserName("Test@qq.com");
        userService.queryByPage(userParam, 0, 10);
    }

    @Test
    public void insert() {
        UserParam userParam = new UserParam();
        userParam.setId("1497750318589411322");
        userParam.setUserName("Test@qq.com");
        userParam.setPassword("123456");
        userParam.setRole(UserRoleEnum.Admin);
        userService.insert(userParam);
    }

    @Test
    public void update() {
        UserParam userParam = new UserParam();
        userParam.setId("1497750318589411322");
        userParam.setNickName("HELLO!");
        userService.update(userParam);
    }

    @Test
    public void deleteById() {
        userService.deleteById("1497750318589411322");
    }

    @Test
    public void selectByUserName() {
        userService.selectByUserName("Test@qq.com");
    }

    @Test
    public void getUserInfo() {
//        UserParam userParam = new UserParam();
//        userParam.setUserName("Test@qq.com");
//        userService.updatePassword(userParam);
    }
}
