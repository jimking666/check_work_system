package com.qushihan.check_work_system.work.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;
import com.qushihan.check_work_system.work.mapper.auto.WorkMapper;
import com.qushihan.check_work_system.work.model.auto.Work;
import com.qushihan.check_work_system.work.model.auto.WorkExample;

@Repository
public class WorkDao {

    @Autowired
    private WorkMapper workMapper;

    /**
     * 通过CourseTeacherClazzId查询作业列表
     *
     * @param courseTeacherClazzId
     *
     * @return
     */
    public List<Work> queryWorkListByCourseTeacherClazzId(Long courseTeacherClazzId) {
        WorkExample workExample = new WorkExample();
        WorkExample.Criteria criteria = workExample.createCriteria();
        criteria.andCourseTeacherClazzIdEqualTo(courseTeacherClazzId);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return workMapper.selectByExample(workExample);
    }

    /**
     * 创建作业
     *
     * @param work
     */
    public int createWork(Work work) {
        return workMapper.insertSelective(work);
    }

    /**
     * 通过作业题目和作业内容查询作业列表
     *
     * @param workTitle
     * @param workContent
     *
     * @return
     */
    public List<Work> queryWorkListByWorkTitleAndWorkContent(String workTitle, String workContent) {
        WorkExample workExample = new WorkExample();
        WorkExample.Criteria criteria = workExample.createCriteria();
        criteria.andWorkTitleEqualTo(workTitle);
        criteria.andWorkContentEqualTo(workContent);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return workMapper.selectByExample(workExample);
    }

    /**
     * 通过作业id更改作业记录
     *
     * @param work
     * @param workId
     *
     * @return
     */
    public int updateWorkByWorkId(Work work, Long workId) {
        WorkExample workExample = new WorkExample();
        WorkExample.Criteria criteria = workExample.createCriteria();
        criteria.andWorkIdEqualTo(workId);
        return workMapper.updateByExampleSelective(work, workExample);
    }
}
