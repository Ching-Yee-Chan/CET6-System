package org.example.controller.exam;

import org.example.common.Result;
import org.example.common.SuperController;
import org.example.pojo.model.UserModel;
import org.example.pojo.param.UserParam;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author Jiankun
 * @since 2023-06-28 23:48:02
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody UserParam user,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.userService.queryByPage(user, pageNum, pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") String id) {
        return success(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody UserParam user) {
        return this.userService.insert(user);
    }

    @PatchMapping("/patch")
    public Result patchInfo(@RequestBody UserParam user){
        userService.patchInfo(user);
        return Result.ok("编辑成功");
    }

    @GetMapping("/info")
    public Result getUserInfo(){
        UserModel userInfo = userService.getUserInfo();
        return Result.ok(userInfo);
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody UserParam user) {
        return success(this.userService.update(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return success(this.userService.deleteById(id));
    }

    /**
     * 注册用户
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping("/registeredUser")
    public Result registeredUser(@RequestBody UserParam user) {
        return this.userService.registeredUser(user);
    }

    @PostMapping("/password")
    public Result updatePassword(@RequestBody UserParam user){
        return this.userService.updatePassword(user);
    }
}

