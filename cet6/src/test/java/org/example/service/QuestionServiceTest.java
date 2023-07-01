package org.example.service;

import org.example.pojo.entity.Question;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;
    @Test
    void queryById() {
        questionService.queryById(38);
    }

    @Test
    void queryByPage() {
        questionService.queryByPage(new Question(), PageRequest.of(0, 10));
    }

    @Test
    void queryAll() {
        questionService.queryAll(new Question());
    }

    @Test
    void insert() {
        Question param = new Question();
        param.setPaperid("1524594652303327232");
        questionService.insert(param);
    }

    @Test
    void update() {
        Question param = new Question();
        param.setPaperid("1524594652303327232");
        param.setAnswer("A");
        questionService.update(param);
    }

    @Test
    void deleteById() {
        questionService.deleteById(52);
    }
}