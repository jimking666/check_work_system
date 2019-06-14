package com.qushihan.check_work_system.course.api.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.qushihan.check_work_system.core.api.CourseTeacherClazzService;
import com.qushihan.check_work_system.core.dto.CourseTeacherClazzDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qushihan.check_work_system.course.api.CourseService;
import com.qushihan.check_work_system.course.dao.CourseDao;
import com.qushihan.check_work_system.course.dto.CourseDto;
import com.qushihan.check_work_system.course.enums.CreateCourseStatus;
import com.qushihan.check_work_system.course.enums.DeleteCourseStatus;
import com.qushihan.check_work_system.course.model.auto.Course;
import com.qushihan.check_work_system.course.util.CourseUtil;
import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseTeacherClazzService courseTeacherClazzService;

    @Override
    public String createCourse(String courseName) {
        // 先查重复
        if (!CollectionUtils.isEmpty(courseDao.queryCourseByCourseName(courseName))) {
            return CreateCourseStatus.REPEAT_CREATE_FAIL.getMessage();
        }
        // 若不重复执行插入
        Long courseId = CourseUtil.getCourseId(courseName);
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseId(courseId);
        courseDao.createCourse(course);
        return CreateCourseStatus.CREATE_SUCCESS.getMessage();
    }

    @Override
    public List<CourseDto> queryAllCourse() {
        List<Course> courses = courseDao.queryAllCourse();
        if (CollectionUtils.isEmpty(courses)) {
            return Collections.emptyList();
        }
        List<CourseDto> courseDtos = courses.stream().map(course -> {
            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(course, courseDto);
            return courseDto;
        }).collect(Collectors.toList());
        return courseDtos;
    }

    @Override
    public String deleteCourse(Long courseId) {
        List<Course> courses = courseDao.getByCourseId(courseId);
        if (CollectionUtils.isEmpty(courses)) {
            return DeleteCourseStatus.NOT_SUCH_COURSE.getMessage();
        }
        List<CourseTeacherClazzDto> courseTeacherClazzDtos = courseTeacherClazzService.getByCourseId(courseId);
        if (!CollectionUtils.isEmpty(courseTeacherClazzDtos)) {
            return DeleteCourseStatus.HAVE_RELEVANCE.getMessage();
        }
        Course course = courses.stream()
                .findFirst()
                .orElse(new Course());
        course.setIsdel(FieldIsdelStatus.ISDEL_TRUE.getIsdel());
        courseDao.updateByCourseId(course);
        return DeleteCourseStatus.DELETE_SUCCESS.getMessage();
    }

    @Override
    public CourseDto queryCourseDtoByCourseId(Long courseId) {
        List<Course> courses = courseDao.getByCourseId(courseId);
        Course course = courses.stream().findFirst().orElse(null);
        if (Optional.ofNullable(course).isPresent()) {
            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(course, courseDto);
            return courseDto;
        }
        return new CourseDto();
    }

    @Override
    public List<CourseDto> getBySearchCourseName(String searchCourseName) {
        List<Course> courses = courseDao.getBySearchCourseName(searchCourseName);
        return courses.stream()
                .map(course -> {
                    CourseDto courseDto = new CourseDto();
                    BeanUtils.copyProperties(course, courseDto);
                    return courseDto;
                })
                .collect(Collectors.toList());
    }
}
