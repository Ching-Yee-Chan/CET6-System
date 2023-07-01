package com.exam.controller.exam;

import com.exam.common.Result;
import com.exam.common.SuperController;
import com.exam.pojo.entity.Question;
import com.exam.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Question)表控制层
 *
 * @author makejava
 * @since 2023-06-26 17:33:14
 */
@RestController
@RequestMapping("question")
public class QuestionController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private QuestionService questionService;

    /**
     * 分页查询
     *
     * @param question 筛选条件
     * @param pageNum  当前页
     * @param pageSize 每页显示数量
     * @return 查询结果
     */
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody Question question,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.questionService.queryByPage(question, PageRequest.of(pageNum - 1, pageSize)));
    }

    /**
     * 查询全部数据
     *
     * @param question  筛选条件
     * @return  查询结果
     */
    @PostMapping("/all")
    public Result queryAll(@RequestBody Question question) {
        return success(this.questionService.queryAll(question));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Question> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.questionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param question 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody Question question) {
        return success(this.questionService.insert(question));
    }

    /**
     * 编辑数据
     *
     * @param question 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody Question question) {
        return success(this.questionService.update(question));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return success(this.questionService.deleteById(id));
    }

}

