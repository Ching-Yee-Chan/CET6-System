package org.example.service.impl;

import org.example.dao.AnswerDao;
import org.example.pojo.entity.Answer;
import org.example.service.AnswerService;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Answer)表服务实现类
 *
 * @author makejava
 * @since 2023-06-26 22:47:25
 */
@Service("answerService")
public class AnswerServiceImpl implements AnswerService {
    @Resource
    private AnswerDao answerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param answerexam 主键
     * @return 实例对象
     */
    @Override
    public Answer queryById(String answerexam) {
        return this.answerDao.queryById(answerexam);
    }

    /**
     * 分页查询
     *
     * @param answer      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo<Answer> queryByPage(Answer answer, PageRequest pageRequest) {
        List<Answer> lists = this.answerDao.queryAllByLimit(answer, pageRequest);
        return new PageInfo<>(lists);
    }

    /**
     * 新增数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    @Override
    public Answer insert(Answer answer) {
        this.answerDao.insert(answer);
        return answer;
    }

    /**
     * 修改数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    @Override
    public Answer update(Answer answer) {
        this.answerDao.update(answer);
        return this.queryById(answer.getAnswerexam());
    }

    /**
     * 通过主键删除数据
     *
     * @param answerexam 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String answerexam) {
        return this.answerDao.deleteById(answerexam) > 0;
    }
}
