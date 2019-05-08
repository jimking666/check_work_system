package com.qushihan.check_work_system.core.api.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qushihan.check_work_system.clazz.api.ClazzService;
import com.qushihan.check_work_system.clazz.dto.ClazzDto;
import com.qushihan.check_work_system.core.api.CourseTeacherClazzService;
import com.qushihan.check_work_system.core.dao.CourseTeacherClazzDao;
import com.qushihan.check_work_system.core.dto.CourseTeacherClazzDto;
import com.qushihan.check_work_system.core.enums.CreateCourseTeacherClazzStatus;
import com.qushihan.check_work_system.core.enums.DeleteCourseTeacherClazzStatus;
import com.qushihan.check_work_system.core.model.auto.CourseTeacherClazz;
import com.qushihan.check_work_system.core.util.CourseTeacherClazzUtil;
import com.qushihan.check_work_system.course.api.CourseService;
import com.qushihan.check_work_system.course.dto.CourseDto;
import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;
import com.qushihan.check_work_system.teacher.api.TeacherService;
import com.qushihan.check_work_system.teacher.dto.TeacherDto;

@Service
public class CourseTeacherClazzServiceImpl implements CourseTeacherClazzService {

    @Autowired
    private CourseTeacherClazzDao courseTeacherClazzDao;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClazzService clazzService;

    @Override
    public String createCourseTeacherClazz(Long courseId, Long teacherId, Long clazzId) {
        // 先查重复
        if (!CollectionUtils.isEmpty(
                courseTeacherClazzDao.queryCourseTeacherClazzListByCourseIdAndTeacherIdAndClazzId(courseId, teacherId,
                                                                                                  clazzId))) {
            return CreateCourseTeacherClazzStatus.REPEAT_CREATE_FAIL.getMessage();
        }
        Long courseTeacherClazzId = CourseTeacherClazzUtil.getCourseTeacherClazzId(courseId, teacherId, clazzId);
        CourseTeacherClazz courseTeacherClazz = new CourseTeacherClazz();
        courseTeacherClazz.setCourseTeacherClazzId(courseTeacherClazzId);
        courseTeacherClazz.setCourseId(courseId);
        courseTeacherClazz.setTeacherId(teacherId);
        courseTeacherClazz.setClazzId(clazzId);
        courseTeacherClazzDao.InsertCourseTeacherClazz(courseTeacherClazz);
        return CreateCourseTeacherClazzStatus.CREATE_SUCCESS.getMessage();
    }

    @Override
    public List<CourseTeacherClazzDto> getByTeacherId(Long teacherId) {
        List<CourseTeacherClazz> courseTeacherClazzes = courseTeacherClazzDao.getByTeacherId(teacherId);
        if (CollectionUtils.isEmpty(courseTeacherClazzes)) {
            return Collections.emptyList();
        }
        List<CourseTeacherClazzDto> courseTeacherClazzDtos = courseTeacherClazzes.stream()
                .map(courseTeacherClazz -> {
                    CourseTeacherClazzDto courseTeacherClazzDto = new CourseTeacherClazzDto();
                    BeanUtils.copyProperties(courseTeacherClazz, courseTeacherClazzDto);
                    CourseDto courseDto = courseService.queryCourseDtoByCourseId(courseTeacherClazz.getCourseId());
                    TeacherDto teacherDto = teacherService.queryTeacherDtoByTeacherId(courseTeacherClazz.getTeacherId());
                    ClazzDto clazzDto = clazzService.queryClazzDtoByClazzId(courseTeacherClazz.getClazzId());
                    courseTeacherClazzDto.setCourseName(courseDto.getCourseName());
                    courseTeacherClazzDto.setTeacherName(teacherDto.getTeacherName());
                    courseTeacherClazzDto.setClazzName(clazzDto.getClazzName());
                    return courseTeacherClazzDto;
                }).collect(Collectors.toList());
        return courseTeacherClazzDtos;
    }

    @Override
    public String deleteCourseTeacherClazz(Long courseTeacherClazzId) {
        courseTeacherClazzDao.deleteCourseTeacherClazzByCourseTeacherClazzId(courseTeacherClazzId);
        return DeleteCourseTeacherClazzStatus.DELETE_SUCCESS.getMessage();
    }

    @Override
    public Integer workCountAddOne(Long courseTeacherClazzId) {
        List<CourseTeacherClazz> courseTeacherClazzes = courseTeacherClazzDao.queryCourseTeacherClazzListByCourseTeacherClazzId(
                courseTeacherClazzId);
        CourseTeacherClazz courseTeacherClazz = courseTeacherClazzes.stream().findFirst().orElse(null);
        Long workCount = courseTeacherClazz.getWorkCount();
        courseTeacherClazz.setWorkCount(workCount + 1);
        if (courseTeacherClazz != null) {
            return courseTeacherClazzDao.workCountUpdate(courseTeacherClazzId, courseTeacherClazz);
        }
        return null;
    }
}
