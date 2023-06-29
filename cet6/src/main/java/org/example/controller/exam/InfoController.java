package org.example.controller.exam;

import org.example.common.Result;
import org.example.common.SuperController;
import org.example.pojo.param.InfoParam;
import org.example.service.InfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Info)表控制层
 *
 * @author Zhiqiang
 * @since 2023-06-11 11:13:06
 */
@RestController
@RequestMapping("/info")
@CrossOrigin
public class InfoController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private InfoService infoService;

    /**
     * 分页查询
     *
     * @param info 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody InfoParam info,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.infoService.queryByPage(info, pageNum, pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") String id) {
        return success(this.infoService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param info 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody InfoParam info) {
        return success(this.infoService.insert(info));
    }

    /**
     * 编辑数据
     *
     * @param info 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody InfoParam info) {
        return success(this.infoService.update(info));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return success(this.infoService.deleteById(id));
    }

}

