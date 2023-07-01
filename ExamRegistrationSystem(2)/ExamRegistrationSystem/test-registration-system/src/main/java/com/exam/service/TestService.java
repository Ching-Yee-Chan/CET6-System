package com.exam.service;

import com.exam.pojo.entity.Test;
import org.springframework.data.domain.PageRequest;
import com.github.pagehelper.PageInfo;

/**
 * (Test)表服务接口
 *
 * @author makejava
 * @since 2023-06-28 22:02:53
 */
public interface TestService {

    /**
     * 通过ID查询单条数据
     *
     * @param 主键
     * @return 实例对象
     */
    Test queryById();

    /**
     * 分页查询
     *
     * @param test        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    PageInfo<Test> queryByPage(Test test, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param test 实例对象
     * @return 实例对象
     */
    Test insert(Test test);

    /**
     * 修改数据
     *
     * @param test 实例对象
     * @return 实例对象
     */
    Test update(Test test);

    /**
     * 通过主键删除数据
     *
     * @param 主键
     * @return 是否成功
     */
    boolean deleteById();

}
