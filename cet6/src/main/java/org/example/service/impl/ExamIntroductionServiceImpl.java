package org.example.service.impl;

import org.example.common.Result;
import org.example.common.Utils.DateUtil;
import org.example.common.Utils.IDGenerator;
import org.example.common.enums.impl.ExamTypeEnum;
import org.example.dao.ExamIntroductionDao;
import org.example.pojo.model.ExamIntroductionModel;
import org.example.pojo.param.ExamIntroductionParam;
import org.example.service.ExamIntroductionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.service.ExamIntroductionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ExamIntroduction)表服务实现类
 *
 * @author Zhiqiang
 * @since 2023-06-29 10:45:20
 */
@Service("examIntroductionService")
public class ExamIntroductionServiceImpl implements ExamIntroductionService {
    @Resource
    private ExamIntroductionDao examIntroductionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ExamIntroductionModel queryById(String id) {
        return this.examIntroductionDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param examIntroduction 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<ExamIntroductionModel> queryByPage(ExamIntroductionParam examIntroduction, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ExamIntroductionModel> examIntroductions =this.examIntroductionDao.queryAllByLimit(examIntroduction);
        return new PageInfo<>(examIntroductions);
    }

    /**
     * 新增数据
     *
     * @param examIntroduction 实例对象
     * @return 实例对象
     */
    @Override
    public ExamIntroductionParam insert(ExamIntroductionParam examIntroduction) {
        examIntroduction.setId(IDGenerator.StringID());
        examIntroduction.setCreateTime(DateUtil.getCurrentTimeMillis());
        examIntroduction.setUpdateTime(DateUtil.getCurrentTimeMillis());
        this.examIntroductionDao.insert(examIntroduction);
        return examIntroduction;
    }

    /**
     * 修改数据
     *
     * @param examIntroduction 实例对象
     * @return 实例对象
     */
    @Override
    public ExamIntroductionModel update(ExamIntroductionParam examIntroduction) {
        examIntroduction.setUpdateTime(DateUtil.getCurrentTimeMillis());
        this.examIntroductionDao.update(examIntroduction);
        return this.queryById(examIntroduction.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(String id) {
        ExamIntroductionModel examIntroductionModel = examIntroductionDao.queryById(id);
        List<ExamIntroductionModel> examIntroductionModels = examIntroductionDao.selectByType(examIntroductionModel.getExamType().getEnumCode());
        if (examIntroductionModels.size()<=1){
            return Result.failed("当前类型考试介绍仅剩1个，请不要删除");
        }
        return Result.ok(this.examIntroductionDao.deleteById(id) > 0);
    }

    @Override
    public ExamIntroductionModel queryByType(String type) {
        ExamIntroductionParam examIntroductionParam = new ExamIntroductionParam();
        ExamIntroductionModel examIntroductionModel = new ExamIntroductionModel();
        ExamTypeEnum examTypeEnum = ExamTypeEnum.valueOf(type);
        examIntroductionParam.setExamType(examTypeEnum);
        List<ExamIntroductionModel> examIntroductionModels = examIntroductionDao.queryAllByLimit(examIntroductionParam);
        if (examIntroductionModels!=null && examIntroductionModels.size()>0){
            examIntroductionModel = examIntroductionModels.get(0);
        }
        return examIntroductionModel;
    }
}
