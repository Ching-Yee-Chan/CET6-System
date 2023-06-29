package org.example.service.impl;

import org.example.common.Utils.DateUtil;
import org.example.common.Utils.IDGenerator;
import org.example.dao.InfoDao;
import org.example.pojo.model.InfoModel;
import org.example.pojo.param.InfoParam;
import org.example.service.InfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Info)表服务实现类
 *
 * @author Zhiqiang
 * @since 2023-06-11 11:13:06
 */
@Service("infoService")
public class InfoServiceImpl implements InfoService {
    @Resource
    private InfoDao infoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public InfoModel queryById(String id) {
        return this.infoDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param info 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<InfoModel> queryByPage(InfoParam info, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<InfoModel> infos =this.infoDao.queryAllByLimit(info);
        return new PageInfo<>(infos);
    }

    /**
     * 新增数据
     *
     * @param info 实例对象
     * @return 实例对象
     */
    @Override
    public InfoParam insert(InfoParam info) {
        info.setId(IDGenerator.StringID());
        info.setCreateTime(DateUtil.getCurrentTimeMillis());
        info.setUpdateTime(DateUtil.getCurrentTimeMillis());
        this.infoDao.insert(info);
        return info;
    }

    /**
     * 修改数据
     *
     * @param info 实例对象
     * @return 实例对象
     */
    @Override
    public InfoModel update(InfoParam info) {
        info.setUpdateTime(DateUtil.getCurrentTimeMillis());
        this.infoDao.update(info);
        return this.queryById(info.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.infoDao.deleteById(id) > 0;
    }
}
