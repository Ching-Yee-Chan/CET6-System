package com.exam.dao;

import com.exam.pojo.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Question)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-26 23:32:39
 */
public interface QuestionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param questionid 主键
     * @return 实例对象
     */
    Question queryById(Integer questionid);

    /**
     * 查询指定行数据
     *
     * @param question 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Question> queryAllByLimit(@Param("param") Question question, @Param("pageable") Pageable pageable);

    /**
     * 查询全部数据
     *
     * @param question  查询条件
     * @return  查询结果
     */
    List<Question> queryAll(@Param("param") Question question);
    /**
     * 统计总行数
     *
     * @param question 查询条件
     * @return 总行数
     */
    long count(Question question);

    /**
     * 新增数据
     *
     * @param question 实例对象
     * @return 影响行数
     */
    int insert(@Param("param") Question question);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Question> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Question> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Question> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Question> entities);

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 影响行数
     */
    int update(@Param("param") Question question);

    /**
     * 通过主键删除数据
     *
     * @param questionid 主键
     * @return 影响行数
     */
    int deleteById(Integer questionid);

}

