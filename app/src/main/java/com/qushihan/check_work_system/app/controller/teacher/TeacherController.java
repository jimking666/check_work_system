package com.qushihan.check_work_system.app.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qushihan.check_work_system.clazz.api.ClazzService;
import com.qushihan.check_work_system.clazz.dto.ClazzDto;
import com.qushihan.check_work_system.core.api.CourseTeacherClazzService;
import com.qushihan.check_work_system.core.dto.CourseTeacherClazzDto;
import com.qushihan.check_work_system.course.api.CourseService;
import com.qushihan.check_work_system.course.dto.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qushihan.check_work_system.app.util.PrintWriterUtil;
import com.qushihan.check_work_system.teacher.api.TeacherService;
import com.qushihan.check_work_system.teacher.dto.LoginTeacherRequest;
import com.qushihan.check_work_system.teacher.dto.RegisterTeacherRequest;
import com.qushihan.check_work_system.teacher.dto.TeacherDto;
import com.qushihan.check_work_system.teacher.enums.JudgeLoginStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTeacherClazzService courseTeacherClazzService;
    /**
     * 教师注册
     *
     * @param registerTeacherRequest
     * @param response
     */
    @PostMapping("/register")
    public void registerTeacher(@RequestBody RegisterTeacherRequest registerTeacherRequest,
            HttpServletResponse response) {
        String teacherNumber = registerTeacherRequest.getTeacherNumber();
        String teacherPassword = registerTeacherRequest.getTeacherPassword();
        String teacherName = registerTeacherRequest.getTeacherName();
        String registerMessge = teacherService.registerTeacher(teacherNumber, teacherPassword, teacherName);
        PrintWriterUtil.print(registerMessge, response);
    }

    /**
     * 教师登陆
     *
     * @param loginTeacherRequest
     * @param request
     * @param response
     */
    @PostMapping("/login")
    public void loginTeacher(@RequestBody LoginTeacherRequest loginTeacherRequest, HttpServletRequest request,
            HttpServletResponse response) {
        String teacherNumber = loginTeacherRequest.getTeacherNumber();
        String teacherPassword = loginTeacherRequest.getTeacherPassword();
        List<TeacherDto> teacherDtos = teacherService.loginTeacher(teacherNumber, teacherPassword);
        String loginMessage = "";
        if (CollectionUtils.isEmpty(teacherDtos)) {
            loginMessage = JudgeLoginStatus.NUMBER_OR_PASSWORD_ERROR.getMessage();
        } else {
            TeacherDto teacherDto = teacherDtos.stream()
                    .findFirst()
                    .orElse(new TeacherDto());
            loginMessage = JudgeLoginStatus.LOGIN_SUCCESS.getMessage();
            List<CourseDto> courseDtos = courseService.queryAllCourse();
            List<ClazzDto> clazzDtos = clazzService.queryAllClazz();
            Long teacherId = Optional.ofNullable(teacherDto)
                    .map(TeacherDto::getTeacherId)
                    .orElse(0L);
            List<CourseTeacherClazzDto> courseTeacherClazzDtos = courseTeacherClazzService.getByTeacherId(teacherId);
            request.getServletContext().setAttribute("courseTeacherClazzDtos", courseTeacherClazzDtos);
            request.getServletContext().setAttribute("courseDtos", courseDtos);
            request.getServletContext().setAttribute("searchCourseDtos", courseDtos);
            request.getServletContext().setAttribute("clazzDtos", clazzDtos);
            request.getServletContext().setAttribute("searchClazzDtos", clazzDtos);
            request.getServletContext().setAttribute("teacherDto", teacherDto);
        }
        PrintWriterUtil.print(loginMessage, response);
    }

    /**
     * 退出登陆
     *
     * @param request
     * @param response
     */
    @GetMapping("/logout")
    public void logoutTeacher(HttpServletRequest request, HttpServletResponse response) {
        request.getServletContext().removeAttribute("teacherDto");
        request.getServletContext().removeAttribute("courseTeacherClazzDtos");
        request.getServletContext().removeAttribute("courseDtos");
        request.getServletContext().removeAttribute("searchCourseDtos");
        request.getServletContext().removeAttribute("clazzDtos");
        request.getServletContext().removeAttribute("searchClazzDtos");
        request.getServletContext().removeAttribute("studentDtos");
        request.getServletContext().removeAttribute("clazzIdForStudent");
        request.getServletContext().removeAttribute("submitWorkDtos");
        request.getServletContext().removeAttribute("workId");
        request.getServletContext().removeAttribute("workDtos");
        request.getServletContext().removeAttribute("courseTeacherClazzId");
    }
}
