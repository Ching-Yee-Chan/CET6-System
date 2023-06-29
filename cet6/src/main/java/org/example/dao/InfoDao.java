package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.pojo.entity.Info;
import org.example.pojo.model.InfoModel;
import org.example.pojo.param.InfoParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Info)表数据库访问层
 *
 * @author Zhiqiang
 * @since 2023-06-11 11:13:06
 */
@Repository
@Mapper
public interface InfoDao extends BaseMapper<Info> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    InfoModel queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param info 查询条件
     * @return 对象列表
     */
    List<InfoModel> queryAllByLimit(InfoParam info);

    /**
     * 新增数据
     *
     * @param info 实例对象
     * @return 影响行数
     */
    int insert(InfoParam info);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Info> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Info> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Info> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Info> entities);

    /**
     * 修改数据
     *
     * @param info 实例对象
     * @return 影响行数
     */
    int update(InfoParam info);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     *根据考试id和类型查询
     */
     Info selectByExamIdAndType(String examId,Long typeId);

}

