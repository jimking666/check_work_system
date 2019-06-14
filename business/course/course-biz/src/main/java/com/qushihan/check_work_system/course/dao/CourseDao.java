package com.qushihan.check_work_system.course.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qushihan.check_work_system.course.mapper.auto.CourseMapper;
import com.qushihan.check_work_system.course.model.auto.Course;
import com.qushihan.check_work_system.course.model.auto.CourseExample;
import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;

@Repository
public class CourseDao {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 创建课程
     *
     * @param course
     *
     * @return
     */
    public int createCourse(Course course) {
        if (!Optional.ofNullable(course).isPresent()) {
            return 0;
        }
        return courseMapper.insertSelective(course);
    }

    /**
     * 查询所有课程
     *
     * @return
     */
    public List<Course> queryAllCourse() {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return courseMapper.selectByExample(courseExample);
    }

    /**
     * 通过课程名称查询课程
     *
     * @param courseName
     *
     * @return
     */
    public List<Course> queryCourseByCourseName(String courseName) {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andCourseNameEqualTo(courseName);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return courseMapper.selectByExample(courseExample);
    }

    /**
     * 通过课程id更改课程记录
     *
     * @param course
     * @param courseId
     *
     * @return
     */
    public int updateCourseByCourseId(Course course, Long courseId) {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        return courseMapper.updateByExampleSelective(course, courseExample);
    }

    /**
     * 根据课程id查询课程列表
     *
     * @param courseId
     *
     * @return
     */
    public List<Course> queryCourseListByCourseId(Long courseId) {
        if (!Optional.ofNullable(courseId).isPresent()) {
            return Collections.EMPTY_LIST;
        }
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return courseMapper.selectByExample(courseExample);
    }

    /**
     * 通过课程名称搜索课程
     *
     * @param searchCourseName
     * @return
     */
    public List<Course> getBySearchCourseName(String searchCourseName) {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        if (StringUtils.isNotEmpty(searchCourseName)) {
            criteria.andCourseNameLike(searchCourseName + "%");
        }
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return courseMapper.selectByExample(courseExample);
    }
}
