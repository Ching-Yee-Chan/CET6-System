package org.example.service.impl;

import org.example.dao.QuestionDao;
import org.example.pojo.entity.Question;
import org.example.service.QuestionService;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Question)表服务实现类
 *
 * @author Yuxuan
 * @since 2023-06-26 17:33:15
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionDao questionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param questionid 主键
     * @return 实例对象
     */
    @Override
    public Question queryById(Integer questionid) {
        return this.questionDao.queryById(questionid);
    }

    /**
     * 分页查询
     *
     * @param question    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo<Question> queryByPage(Question question, PageRequest pageRequest) {
        List<Question> lists = this.questionDao.queryAllByLimit(question, pageRequest);
        return new PageInfo<>(lists);
    }

    /**
     * 查询全部数据
     *
     * @param question  筛选条件
     * @return 查询结果
     */
    @Override
    public PageInfo<Question> queryAll(Question question) {
        List<Question> lists = this.questionDao.queryAll(question);
        return new PageInfo<>(lists);
    }

    /**
     * 新增数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    @Override
    public Question insert(Question question) {
        this.questionDao.insert(question);
        return question;
    }

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    @Override
    public Question update(Question question) {
        this.questionDao.update(question);
        return this.queryById(question.getQuestionid());
    }

    /**
     * 通过主键删除数据
     *
     * @param questionid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer questionid) {
        return this.questionDao.deleteById(questionid) > 0;
    }
}
