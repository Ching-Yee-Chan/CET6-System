package org.example.controller.exam;

import org.example.common.Result;
import org.example.common.SuperController;
import org.example.pojo.entity.Answer;
import org.example.service.AnswerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Answer)表控制层
 *
 * @author makejava
 * @since 2023-06-26 22:47:23
 */
@RestController
@RequestMapping("answer")
public class AnswerController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private AnswerService answerService;

    /**
     * 分页查询
     *
     * @param answer   筛选条件
     * @param pageNum  当前页
     * @param pageSize 每页显示数量
     * @return 查询结果
     */
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody Answer answer,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.answerService.queryByPage(answer, PageRequest.of(pageNum - 1, pageSize)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Answer> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.answerService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param answer 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody Answer answer) {
        return success(this.answerService.insert(answer));
    }

    /**
     * 编辑数据
     *
     * @param answer 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody Answer answer) {
        return success(this.answerService.update(answer));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return success(this.answerService.deleteById(id));
    }

}

