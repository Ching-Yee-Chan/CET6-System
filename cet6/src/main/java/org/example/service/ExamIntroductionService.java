package org.example.service;

import org.example.common.Result;
import org.example.pojo.model.ExamIntroductionModel;
import org.example.pojo.param.ExamIntroductionParam;
import com.github.pagehelper.PageInfo;

/**
 * (ExamIntroduction)表服务接口
 *
 * @author Zhiqiang
 * @since 2023-06-29 10:45:20
 */
public interface ExamIntroductionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamIntroductionModel queryById(String id);

    /**
     * 分页查询
     *
     * @param examIntroduction 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<ExamIntroductionModel> queryByPage(ExamIntroductionParam examIntroduction, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param examIntroduction 实例对象
     * @return 实例对象
     */
    ExamIntroductionParam insert(ExamIntroductionParam examIntroduction);

    /**
     * 修改数据
     *
     * @param examIntroduction 实例对象
     * @return 实例对象
     */
    ExamIntroductionModel update(ExamIntroductionParam examIntroduction);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(String id);

    ExamIntroductionModel queryByType(String type);
}
