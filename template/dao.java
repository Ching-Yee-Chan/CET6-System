##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Dao"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/dao"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}dao;

import $!{tableInfo.savePackageName}.pojo.entity.$!{tableInfo.name};
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表数据库访问层
 *
 * @author $!author
 * @since $!time.currTime()
 */
public interface $!{tableName} {

    /**
     * 通过ID查询单条数据
     *
     * @param $!pk.name 主键
     * @return 实例对象
     */
    $!{tableInfo.name} queryById($!pk.shortType $!pk.name);

    /**
     * 查询指定行数据
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<$!{tableInfo.name}> queryAllByLimit(@Param("param")$!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}), @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 查询条件
     * @return 总行数
     */
    long count($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));

    /**
     * 新增数据
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * @return 影响行数
     */
    int insert(@Param("param")$!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<$!{tableInfo.name}> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<$!{tableInfo.name}> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<$!{tableInfo.name}> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<$!{tableInfo.name}> entities);

    /**
     * 修改数据
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * @return 影响行数
     */
    int update($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));

    /**
     * 通过主键删除数据
     *
     * @param $!pk.name 主键
     * @return 影响行数
     */
    int deleteById($!pk.shortType $!pk.name);

}
