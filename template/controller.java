##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Controller"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/controller"))
##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}controller;

import com.exam.common.Result;
import com.exam.common.SuperController;
import $!{tableInfo.savePackageName}.pojo.entity.$!{tableInfo.name};
import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表控制层
 *
 * @author $!author
 * @since $!time.currTime()
 */
@RestController
@RequestMapping("$!tool.firstLowerCase($tableInfo.name)")
public class $!{tableName} extends SuperController{
    /**
     * 服务对象
     */
    @Resource
    private $!{tableInfo.name}Service $!tool.firstLowerCase($tableInfo.name)Service;

    /**
     * 分页查询
     *
     * @param $!{tool.firstLowerCase($tableInfo.name)} 筛选条件
     * @param pageNum           当前页
     * @param pageSize          每页显示数量
     * @return 查询结果
     */
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage($!{tableInfo.name} $!{tool.firstLowerCase($tableInfo.name)}, 
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.$!{tool.firstLowerCase($tableInfo.name)}Service.queryByPage($!{tool.firstLowerCase($tableInfo.name)}, PageRequest.of(pageNum-1, pageSize)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<$!{tableInfo.name}> queryById(@PathVariable("id") $!pk.shortType id) {
        return ResponseEntity.ok(this.$!{tool.firstLowerCase($tableInfo.name)}Service.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param $!{tool.firstLowerCase($tableInfo.name)} 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody $!{tableInfo.name} $!{tool.firstLowerCase($tableInfo.name)}) {
        return success(this.$!{tool.firstLowerCase($tableInfo.name)}Service.insert($!{tool.firstLowerCase($tableInfo.name)}));
    }

    /**
     * 编辑数据
     *
     * @param $!{tool.firstLowerCase($tableInfo.name)} 实体
     * @return 编辑结果
     */
    @PutMapping
    public Request edit($!{tableInfo.name} $!{tool.firstLowerCase($tableInfo.name)}) {
        return success(this.$!{tool.firstLowerCase($tableInfo.name)}Service.update($!{tool.firstLowerCase($tableInfo.name)}));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public Request deleteById(@PathVariable("id") $!pk.shortType id) {
        return success(this.$!{tool.firstLowerCase($tableInfo.name)}Service.deleteById(id));
    }

}
