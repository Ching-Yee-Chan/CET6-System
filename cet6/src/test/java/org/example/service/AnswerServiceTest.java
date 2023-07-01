package org.example.service;

import org.example.pojo.entity.Answer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AnswerServiceTest {

    @Autowired
    private AnswerService answerService;

    @Test
    void queryById() {
        answerService.queryById("1530108947183173632");
    }

    @Test
    void queryByPage() {
        answerService.queryByPage(new Answer(), PageRequest.of(0, 10));
    }

    @Test
    void insert() {
        Answer param = new Answer();
        param.setAnswerexam("1530108947183173632");
        param.setAnsweruser("1497750318589411320");
        param.setAnswerquestion(40);
        answerService.insert(param);
    }

    @Test
    void update() {
        Answer param = new Answer();
        param.setAnswerexam("1530108947183173632");
        param.setAnsweruser("1497750318589411320");
        param.setAnswerquestion(40);
        param.setAnsweroption(1);
        answerService.update(param);
    }

    @Test
    void deleteById() {
        answerService.deleteById("1530108947183173632");
    }
}