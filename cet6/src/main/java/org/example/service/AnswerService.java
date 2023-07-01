package org.example.service;

import org.example.pojo.entity.Answer;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * (Answer)表服务接口
 *
 * @author Yuxuan
 * @since 2023-06-26 22:47:25
 */
public interface AnswerService {

    /**
     * 通过ID查询单条数据
     *
     * @param answerexam 主键
     * @return 实例对象
     */
    Answer queryById(String answerexam);

    /**
     * 分页查询
     *
     * @param answer      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    PageInfo<Answer> queryByPage(Answer answer, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    Answer insert(Answer answer);

    /**
     * 修改数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    Answer update(Answer answer);

    /**
     * 通过主键删除数据
     *
     * @param answerexam 主键
     * @return 是否成功
     */
    boolean deleteById(String answerexam);

}
