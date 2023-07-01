package org.example.service;

import org.example.pojo.param.ExamUserParam;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ExamUserServiceTest {

    @Autowired
    private ExamUserService examUserService;

    @Test
    void queryById() {
        examUserService.queryById("1530107762262933504");
    }

    @Test
    void queryByPage() {
        examUserService.queryByPage(new ExamUserParam(), 0 ,10);
    }

    @Test
    void insert() {
//        ExamUserParam param = new ExamUserParam();
//        param.setId("15301077622629335222");
//        examUserService.insert(param);
    }

    @Test
    void update() {
//        ExamUserParam param = new ExamUserParam();
//        param.setId("15301077622629335222");
//        param.setScore("10");
//        examUserService.insert(param);
    }

    @Test
    void deleteById() {
        examUserService.deleteById("15301077622629335222");
    }

    @Test
    void queryByPageAndUser() {
//        examUserService.queryByPageAndUser(new ExamUserParam(), 0 ,10);
    }

    @Test
    void queryByPageAndPrint() {
//        examUserService.queryByPageAndPrint(new ExamUserParam(), 0 ,10);
    }

    @Test
    void queryScore() {
//        examUserService.queryScore("1530107762262933504");
    }

    @Test
    void queryExamName() {
        examUserService.queryExamName();
    }
}