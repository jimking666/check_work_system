package com.qushihan.check_work_system.app.controller.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qushihan.check_work_system.app.util.PrintWriterUtil;
import com.qushihan.check_work_system.inf.util.TransitionUtil;
import com.qushihan.check_work_system.student.api.StudentService;
import com.qushihan.check_work_system.student.dto.StudentDetailRequest;
import com.qushihan.check_work_system.student.dto.StudentDto;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 学生详情
     *
     * @param studentDetailRequest
     * @param request
     * @param response
     */
    @RequestMapping("/studentDetail")
    public void studentDetail(@RequestBody StudentDetailRequest studentDetailRequest, HttpServletRequest request,
            HttpServletResponse response) {
        Long clazzId = TransitionUtil.stringToLong(studentDetailRequest.getClazzId());
        List<StudentDto> studentDtos = studentService.queryStudentDtoListByClazzId(clazzId);
        request.getServletContext().setAttribute("studentDtos", studentDtos);
        PrintWriterUtil.print("学生详情查询成功", response);
    }
}
