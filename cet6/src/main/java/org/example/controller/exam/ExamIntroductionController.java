package org.example.controller.exam;

import org.example.common.Result;
import org.example.common.SuperController;
import org.example.pojo.param.ExamIntroductionParam;
import org.example.service.ExamIntroductionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ExamIntroduction)表控制层
 *
 * @author Zhiqiang
 * @since 2023-06-29 10:45:20
 */
@RestController
@RequestMapping("/examIntroduction")
@CrossOrigin
public class ExamIntroductionController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private ExamIntroductionService examIntroductionService;

    /**
     * 分页查询
     *
     * @param examIntroduction 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody ExamIntroductionParam examIntroduction,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.examIntroductionService.queryByPage(examIntroduction, pageNum, pageSize));
    }

    /**
     * 根据类型查询
     * @return 查询结果
     */
    @GetMapping("type")
    public Result queryByType(String typeEnum) {
        return success(this.examIntroductionService.queryByType(typeEnum));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") String id) {
        return success(this.examIntroductionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param examIntroduction 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody ExamIntroductionParam examIntroduction) {
        return success(this.examIntroductionService.insert(examIntroduction));
    }

    /**
     * 编辑数据
     *
     * @param examIntroduction 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody ExamIntroductionParam examIntroduction) {
        return success(this.examIntroductionService.update(examIntroduction));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return this.examIntroductionService.deleteById(id);
    }

}

