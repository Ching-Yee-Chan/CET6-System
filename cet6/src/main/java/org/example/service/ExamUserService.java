package org.example.service;

import org.example.pojo.model.ExamModel;
import org.example.pojo.model.ExamUserModel;
import org.example.pojo.param.ExamUserParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (ExamUser)表服务接口
 *
 * @author Bofan
 * @since 2023-06-25 10:23:49
 */
public interface ExamUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamUserModel queryById(String id);

    /**
     * 分页查询
     *
     * @param examUser 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<ExamUserModel> queryByPage(ExamUserParam examUser, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param examUser 实例对象
     * @return 实例对象
     */
    ExamUserParam insert(ExamUserParam examUser);

    /**
     * 修改数据
     *
     * @param examUser 实例对象
     * @return 实例对象
     */
    ExamUserModel update(ExamUserParam examUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    PageInfo<ExamUserModel> queryByPageAndUser(ExamUserParam examUser, Integer pageNum, Integer pageSize);

    PageInfo<ExamUserModel> queryByPageAndPrint(ExamUserParam examUser, Integer pageNum, Integer pageSize);

    List<ExamUserModel> queryScore();

    List<ExamModel> queryExamName();
}
