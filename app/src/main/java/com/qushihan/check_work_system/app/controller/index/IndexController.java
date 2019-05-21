package com.qushihan.check_work_system.app.controller.index;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qushihan.check_work_system.clazz.api.ClazzService;
import com.qushihan.check_work_system.clazz.dto.ClazzDto;
import com.qushihan.check_work_system.core.api.CourseTeacherClazzService;
import com.qushihan.check_work_system.core.dto.CourseTeacherClazzDto;
import com.qushihan.check_work_system.course.api.CourseService;
import com.qushihan.check_work_system.course.dto.CourseDto;
import com.qushihan.check_work_system.teacher.dto.TeacherDto;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private CourseTeacherClazzService courseTeacherClazzService;

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String forwardLogin() {
        return "login";
    }

    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String forwardRegister() {
        return "register";
    }

//    /**
//     * 作业管理页面
//     *
//     * @param map
//     *
//     * @return
//     */
//    @RequestMapping("/workManagement")
//    public String forwardWorkManagement(Map map, HttpServletRequest request) {
//        List<CourseDto> courseDtos = courseService.queryAllCourse();
//        List<ClazzDto> clazzDtos = clazzService.queryAllClazz();
//        TeacherDto teacherDto = (TeacherDto) request.getServletContext().getAttribute("teacherDto");
//        Long teacherId = Optional.ofNullable(teacherDto)
//                .map(TeacherDto::getTeacherId)
//                .orElse(0L);
//        List<CourseTeacherClazzDto> courseTeacherClazzDtos = courseTeacherClazzService.getByTeacherId(teacherId);
//        map.put("courseDtos", courseDtos);
//        map.put("clazzDtos", clazzDtos);
//        map.put("courseTeacherClazzDtos", courseTeacherClazzDtos);
//        return "workManagement";
//    }

    /**
     * 作业管理页面
     *
     * @return
     */
    @RequestMapping("/workManagement")
    public String forwardWorkManagement() {
        return "workManagement";
    }

//    /**
//     * 课程管理页面
//     *
//     * @param map
//     *
//     * @return
//     */
//    @RequestMapping("/courseManagement")
//    public String forwardCourseManagement(Map map, HttpServletRequest request) {
//        List<CourseDto> courseDtos = courseService.queryAllCourse();
//        List<ClazzDto> clazzDtos = clazzService.queryAllClazz();
//        TeacherDto teacherDto = (TeacherDto) request.getServletContext().getAttribute("teacherDto");
//        Long teacherId = Optional.ofNullable(teacherDto)
//                .map(TeacherDto::getTeacherId)
//                .orElse(0L);
//        List<CourseTeacherClazzDto> courseTeacherClazzDtos = courseTeacherClazzService.getByTeacherId(teacherId);
//        map.put("courseDtos", courseDtos);
//        map.put("clazzDtos", clazzDtos);
//        map.put("courseTeacherClazzDtos", courseTeacherClazzDtos);
//        return "courseManagement";
//    }

    /**
     * 课程管理页面
     *
     * @return
     */
    @RequestMapping("/courseManagement")
    public String forwardCourseManagement() {
        return "courseManagement";
    }

//    /**
//     * 班级管理页面
//     *
//     * @param map
//     *
//     * @return
//     */
//    @RequestMapping("/clazzManagement")
//    public String forwardClazzManagement(Map map, HttpServletRequest request) {
//        List<CourseDto> courseDtos = courseService.queryAllCourse();
//        List<ClazzDto> clazzDtos = clazzService.queryAllClazz();
//        TeacherDto teacherDto = (TeacherDto) request.getServletContext().getAttribute("teacherDto");
//        Long teacherId = Optional.ofNullable(teacherDto)
//                .map(TeacherDto::getTeacherId)
//                .orElse(0L);
//        List<CourseTeacherClazzDto> courseTeacherClazzDtos = courseTeacherClazzService.getByTeacherId(teacherId);
//        map.put("courseDtos", courseDtos);
//        map.put("clazzDtos", clazzDtos);
//        map.put("courseTeacherClazzDtos", courseTeacherClazzDtos);
//        return "clazzManagement";
//    }

    /**
     * 班级管理页面
     *
     * @return
     */
    @RequestMapping("/clazzManagement")
    public String forwardClazzManagement() {
        return "clazzManagement";
    }

    /**
     * 班级学生详情页面
     *
     * @return
     */
    @RequestMapping("/clazzStudentDetail")
    public String forwardClazzStudentDetail() {
        return "clazzStudentDetail";
    }

    /**
     * 发布作业详情页面
     *
     * @return
     */
    @RequestMapping("/releaseWorkDetail")
    public String forwardReleaseWorkDetail() {
        return "releaseWorkDetail";
    }

    /**
     * 提交作业详情页面
     *
     * @return
     */
    @RequestMapping("/submitWorkDetail")
    public String forwardSubmitWorkDetail() {
        return "submitWorkDetail";
    }
}
