package org.example.service;

import org.example.common.enums.impl.ExamTypeEnum;
import org.example.pojo.param.ExamIntroductionParam;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExamIntroductionServiceTest {

    @Autowired
    private ExamIntroductionService examIntroductionService;
    @Test
    void queryById() {
        examIntroductionService.queryById("14638243696156344323");
    }

    @Test
    void queryByPage() {
        ExamIntroductionParam param = new ExamIntroductionParam();
        examIntroductionService.queryByPage(param, 0, 10);
    }

    @Test
    void insert() {
        ExamIntroductionParam param = new ExamIntroductionParam();
        param.setId("146382436961563443445");
        examIntroductionService.insert(param);
    }

    @Test
    void update() {
        ExamIntroductionParam param = new ExamIntroductionParam();
        param.setId("146382436961563443445");
        param.setDescription("test");
        examIntroductionService.update(param);
    }

    @Test
    void deleteById() {
        examIntroductionService.deleteById("1674980609912471552");
    }

    @Test
    void queryByType() {
        examIntroductionService.queryByType(String.valueOf(ExamTypeEnum.Cet_Band_4));
    }
}