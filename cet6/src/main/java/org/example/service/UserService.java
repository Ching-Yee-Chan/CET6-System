package org.example.service;

import org.example.common.Result;
import com.github.pagehelper.PageInfo;
import org.example.pojo.model.UserModel;
import org.example.pojo.param.UserParam;


/**
 * (User)表服务接口
 *
 * @author Jiankun
 * @since 2023-06-28 23:48:02
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserModel queryById(String id);

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<UserModel> queryByPage(UserParam user, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Result insert(UserParam user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    UserModel update(UserParam user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    UserModel selectByUserName(String username);

    Result registeredUser(UserParam user);

    void patchInfo(UserParam user);

    Result updatePassword(UserParam user);

    UserModel getUserInfo();
}
