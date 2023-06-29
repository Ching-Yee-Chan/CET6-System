package org.example.service;

import org.example.common.Result;
import com.github.pagehelper.PageInfo;
import org.example.pojo.model.ExamModel;
import org.example.pojo.param.ExamParam;

import java.util.List;

/**
 * (Exam)表服务接口
 *
 * @author Jiankun
 * @since 2023-06-27 21:28:13
 */
public interface ExamService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamModel queryById(String id);

    /**
     * 分页查询
     *
     * @param exam 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<ExamModel> queryByPage(ExamParam exam, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    ExamParam insert(ExamParam exam);

    /**
     * 修改数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    ExamModel update(ExamParam exam);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(String id);

    List<ExamModel> queryByExamType(String exampType);
}
