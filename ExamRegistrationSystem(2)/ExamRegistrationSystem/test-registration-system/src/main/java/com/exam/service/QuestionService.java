package com.exam.service;

import com.exam.pojo.entity.Question;
import org.springframework.data.domain.PageRequest;
import com.github.pagehelper.PageInfo;

/**
 * (Question)表服务接口
 *
 * @author makejava
 * @since 2023-06-26 17:33:15
 */
public interface QuestionService {

    /**
     * 通过ID查询单条数据
     *
     * @param questionid 主键
     * @return 实例对象
     */
    Question queryById(Integer questionid);

    /**
     * 分页查询
     *
     * @param question    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    PageInfo<Question> queryByPage(Question question, PageRequest pageRequest);

    /**
     * 查询全部数据
     *
     * @param question  筛选条件
     * @return 查询结果
     */
    PageInfo<Question> queryAll(Question question);

    /**
     * 新增数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    Question insert(Question question);

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    Question update(Question question);

    /**
     * 通过主键删除数据
     *
     * @param questionid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer questionid);

}
