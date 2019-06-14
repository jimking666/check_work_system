package com.qushihan.check_work_system.clazz.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qushihan.check_work_system.clazz.mapper.auto.ClazzMapper;
import com.qushihan.check_work_system.clazz.model.auto.Clazz;
import com.qushihan.check_work_system.clazz.model.auto.ClazzExample;
import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;

@Repository
public class ClazzDao {

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 创建班级
     *
     * @param clazz
     *
     * @return
     */
    public int createClazz(Clazz clazz) {
        return clazzMapper.insertSelective(clazz);
    }

    /**
     * 查询所有班级
     *
     * @return
     */
    public List<Clazz> queryAllClazz() {
        ClazzExample clazzExample = new ClazzExample();
        ClazzExample.Criteria criteria = clazzExample.createCriteria();
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return clazzMapper.selectByExample(clazzExample);
    }

    /**
     * 通过班级名称查找班级
     *
     * @param clazzName
     *
     * @return
     */
    public List<Clazz> queryClazzByClazzName(String clazzName) {
        ClazzExample clazzExample = new ClazzExample();
        ClazzExample.Criteria criteria = clazzExample.createCriteria();
        criteria.andClazzNameEqualTo(clazzName);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return clazzMapper.selectByExample(clazzExample);
    }

    /**
     * 通过班级id查找班级列表
     *
     * @param clazzId
     *
     * @return
     */
    public List<Clazz> getByClazzId(Long clazzId) {
        if (!Optional.ofNullable(clazzId).isPresent()) {
            return Collections.emptyList();
        }
        ClazzExample clazzExample = new ClazzExample();
        ClazzExample.Criteria criteria = clazzExample.createCriteria();
        criteria.andClazzIdEqualTo(clazzId);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return clazzMapper.selectByExample(clazzExample);
    }

    /**
     * 通过班级id更改班级记录
     *
     * @param clazz
     *
     * @return
     */
    public int updateByClazzId(Clazz clazz) {
        if (!Optional.ofNullable(clazz).isPresent()) {
            return 0;
        }
        ClazzExample clazzExample = new ClazzExample();
        ClazzExample.Criteria criteria = clazzExample.createCriteria();
        criteria.andClazzIdEqualTo(clazz.getClazzId());
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return clazzMapper.updateByExampleSelective(clazz, clazzExample);
    }

    /**
     * 通过班级名称搜索班级
     *
     * @param searchClazzName
     * @return
     */
    public List<Clazz> getBySearchClazzName(String searchClazzName) {
        ClazzExample clazzExample = new ClazzExample();
        ClazzExample.Criteria criteria = clazzExample.createCriteria();
        if (StringUtils.isNotEmpty(searchClazzName)) {
            criteria.andClazzNameLike(searchClazzName + "%");
        }
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return clazzMapper.selectByExample(clazzExample);
    }
}
