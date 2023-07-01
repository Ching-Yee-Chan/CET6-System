package org.example.service;

import org.example.pojo.param.InfoParam;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class InfoServiceTest {

    @Autowired
    private InfoService infoService;

    @Test
    void queryById() {
        infoService.queryById("1530092271884566528");
    }

    @Test
    void queryByPage() {
        infoService.queryByPage(new InfoParam(), 0 ,10);
    }

    @Test
    void insert() {
        InfoParam param = new InfoParam();
        param.setId("15300922718845665555");
        infoService.insert(param);
    }

    @Test
    void update() {
        InfoParam param = new InfoParam();
        param.setId("15300922718845665555");
        param.setTitle("Test");
        infoService.update(param);
    }

    @Test
    void deleteById() {
        infoService.deleteById("15300922718845665555");
    }
}