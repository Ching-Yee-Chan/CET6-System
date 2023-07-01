package com.exam.dao;

import com.exam.pojo.entity.Answer;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Answer)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-26 23:31:07
 */
public interface AnswerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param answerexam 主键
     * @return 实例对象
     */
    Answer queryById(String answerexam);

    /**
     * 查询指定行数据
     *
     * @param answer   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Answer> queryAllByLimit(@Param("param") Answer answer, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param answer 查询条件
     * @return 总行数
     */
    long count(Answer answer);

    /**
     * 新增数据
     *
     * @param answer 实例对象
     * @return 影响行数
     */
    int insert(@Param("param") Answer answer);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Answer> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Answer> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Answer> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Answer> entities);

    /**
     * 修改数据
     *
     * @param answer 实例对象
     * @return 影响行数
     */
    int update(@Param("param") Answer answer);

    /**
     * 通过主键删除数据
     *
     * @param answerexam 主键
     * @return 影响行数
     */
    int deleteById(String answerexam);

}

