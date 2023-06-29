package org.example.controller.exam;

import org.example.common.Result;
import org.example.common.SuperController;
import org.example.dao.ExamUserDao;
import org.example.pojo.param.ExamParam;
import org.example.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Exam)表控制层
 *
 * @author Jiankun
 * @since 2023-06-28 23:58:20
 */
@RestController
@RequestMapping("/exam")
@CrossOrigin
public class ExamController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private ExamService examService;

    @Autowired
    private ExamUserDao examUserDao;

    /**
     * 分页查询
     *
     * @param exam 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody ExamParam exam,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.examService.queryByPage(exam, pageNum, pageSize));
    }

    @GetMapping("/examType/{type}")
    public Result queryByExamType(@PathVariable("type") String examType)
                              {
        return success(this.examService.queryByExamType(examType));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") String id) {
        return success(this.examService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param exam 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody ExamParam exam) {
        return success(this.examService.insert(exam));
    }

    /**
     * 编辑数据
     *
     * @param exam 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody ExamParam exam) {
        return success(this.examService.update(exam));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return this.examService.deleteById(id);
    }

}

