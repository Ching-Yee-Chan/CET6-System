package org.example.controller.exam;

import org.example.common.Result;
import org.example.common.SuperController;
import org.example.pojo.param.ExamUserParam;
import org.example.security.util.GetTokenInfoUtil;
import org.example.service.ExamUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ExamUser)表控制层
 *
 * @author Bofan
 * @since 2023-06-25 10:23:49
 */
@RestController
@RequestMapping("/examUser")
@CrossOrigin
public class ExamUserController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private ExamUserService examUserService;

    /**
     * 分页查询
     *
     * @param examUser 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody ExamUserParam examUser,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.examUserService.queryByPage(examUser, pageNum, pageSize));
    }

    @GetMapping("/score")
    public Result queryScore(){
        return success(this.examUserService.queryScore());
    }

    @GetMapping("/examName")
    public Result queryExamName(){
        return success(this.examUserService.queryExamName());
    }

    /**
     * 分页查询
     *
     * @param examUser 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @PostMapping("/print/{pageNum}/{pageSize}")
    public Result queryByPageAndPrint(@RequestBody ExamUserParam examUser,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.examUserService.queryByPageAndPrint(examUser, pageNum, pageSize));
    }

    /**
     * 分页查询用户
     *
     * @param examUser 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @PostMapping("/user/{pageNum}/{pageSize}")
    public Result queryByPageAndUser(@RequestBody ExamUserParam examUser,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.examUserService.queryByPageAndUser(examUser, pageNum, pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") String id) {
        return success(this.examUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param examUser 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody ExamUserParam examUser) {
        return success(this.examUserService.insert(examUser));
    }

    /**
     * 编辑数据
     *
     * @param examUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody ExamUserParam examUser) {
        return success(this.examUserService.update(examUser));
    }

    /**
     * 编辑数据
     *
     * @param examUser 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping
    public Result edit(@RequestBody ExamUserParam examUser) {
        return success(this.examUserService.update(examUser));
    }

    /**
     * 更新分数
     * @param examUser 包含 examId score
     * @return
     */
    @ApiOperation("更新分数")
    @PutMapping("/addScore")
    public Result addScore(@RequestBody ExamUserParam examUser) {
        System.out.println("123456");
        ExamUserParam param = new ExamUserParam();
        param.setExamId(examUser.getExamId());
        ExamUserModel updateParam = this.examUserService.queryAll(param).getList().get(0);
        System.out.println(Integer.parseInt(updateParam.getScore()) + Integer.parseInt(examUser.getScore()));
        ExamUserParam param2 = new ExamUserParam();
        param2.setId(updateParam.getId());
        param2.setScore(String.valueOf(Integer.parseInt(updateParam.getScore()) + Integer.parseInt(examUser.getScore())));
        System.out.println(param2.getScore());
        return success(this.examUserService.update(param2));
    }



    /**
     * 编辑数据
     *
     * @param examUser 实体
     * @return 编辑结果
     */
    @PatchMapping("/patch")
    public Result patchInfo(@RequestBody ExamUserParam examUser) {
        String username = GetTokenInfoUtil.getUsername();
        examUser.setUserId(username);
        return success(this.examUserService.update(examUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return success(this.examUserService.deleteById(id));
    }

}

