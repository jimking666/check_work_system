package com.qushihan.check_work_system.app.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 教师注册
     *
     * @param registerTeacherRequest
     * @param response
     */
    @PostMapping("/register")
    public void registerTeacher(@RequestBody RegisterTeacherRequest registerTeacherRequest,
            HttpServletResponse response) {
        String registerMessge = teacherService.registerTeacher(registerTeacherRequest);
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
        TeacherDto teacherDto = teacherService.loginTeacher(loginTeacherRequest);
        String loginMessage = JudgeLoginStatus.UNKNOW.getMessage();
        if (teacherDto == null) {
            loginMessage = JudgeLoginStatus.NUMBER_OR_PASSWORD_ERROR.getMessage();
        } else if (teacherDto.getIsdel()) {
            loginMessage = JudgeLoginStatus.ACCOUNT_DISABLED.getMessage();
        } else {
            loginMessage = JudgeLoginStatus.LOGIN_SUCCESS.getMessage();
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
    }
}
