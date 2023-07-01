package org.example.service;

import org.example.common.enums.impl.ExamTypeEnum;
import org.example.pojo.param.ExamParam;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExamServiceTest {


    @Autowired
    private ExamService examService;

    @Test
    void queryById() {
        examService.queryById("1513853055060148224");
    }

    @Test
    void queryByPage() {
        examService.queryByPage(new ExamParam(), 0 ,10);
    }

    @Test
    void insert() {
//        ExamParam param = new ExamParam();
//        param.setId("1513853055060148224");
//        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = principal
//        examService.insert(param);
    }

    @Test
    void update() {
        ExamParam param = new ExamParam();
        param.setId("1513853055060148224");
        param.setTitle("test");
        examService.update(param);
    }

    @Test
    void deleteById() {
        examService.deleteById("1513853055060148224");
    }

    @Test
    void queryByExamType() {
        examService.queryByExamType(String.valueOf(ExamTypeEnum.Cet_Band_6));
    }
}