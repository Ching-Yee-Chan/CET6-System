package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.common.enums.EnumCode;
import org.example.pojo.entity.ExamIntroduction;
import org.example.pojo.model.ExamIntroductionModel;
import org.example.pojo.param.ExamIntroductionParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (ExamIntroduction)表数据库访问层
 *
 * @author Zhiqiang
 * @since 2023-06-29 10:45:20
 */
@Repository
@Mapper
public interface ExamIntroductionDao extends BaseMapper<ExamIntroduction> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamIntroductionModel queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param examIntroduction 查询条件
     * @return 对象列表
     */
    List<ExamIntroductionModel> queryAllByLimit(ExamIntroductionParam examIntroduction);

    /**
     * 新增数据
     *
     * @param examIntroduction 实例对象
     * @return 影响行数
     */
    int insert(ExamIntroductionParam examIntroduction);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExamIntroduction> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExamIntroduction> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExamIntroduction> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExamIntroduction> entities);

    /**
     * 修改数据
     *
     * @param examIntroduction 实例对象
     * @return 影响行数
     */
    int update(ExamIntroductionParam examIntroduction);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    List<ExamIntroductionModel> selectByType(EnumCode enumCode);
}

