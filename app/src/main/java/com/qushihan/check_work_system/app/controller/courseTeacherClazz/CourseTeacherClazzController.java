package com.qushihan.check_work_system.app.controller.courseTeacherClazz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qushihan.check_work_system.app.util.PrintWriterUtil;
import com.qushihan.check_work_system.core.api.CourseTeacherClazzService;
import com.qushihan.check_work_system.core.dto.CreateCourseTeacherClazzRequest;
import com.qushihan.check_work_system.core.dto.DeleteCourseTeacherClazzRequest;
import com.qushihan.check_work_system.inf.util.TransitionUtil;

@RestController
@RequestMapping("/courseTeacherClazz")
public class CourseTeacherClazzController {

    @Autowired
    private CourseTeacherClazzService courseTeacherClazzService;

    /**
     * 创建课程教师班级
     *
     * @param createCourseTeacherClazzRequest
     * @param response
     */
    @PostMapping("/create")
    public void createCourseTeacherClazz(@RequestBody CreateCourseTeacherClazzRequest createCourseTeacherClazzRequest,
            HttpServletResponse response) {
        Long courseId = TransitionUtil.stringToLong(createCourseTeacherClazzRequest.getCourseId());
        Long teacherId = TransitionUtil.stringToLong(createCourseTeacherClazzRequest.getTeacherId());
        Long clazzId = TransitionUtil.stringToLong(createCourseTeacherClazzRequest.getClazzId());
        String createMessage = courseTeacherClazzService.createCourseTeacherClazz(courseId, teacherId, clazzId);
        PrintWriterUtil.print(createMessage, response);
    }

    /**
     * 删除课程教师班级
     *
     * @param deleteCourseTeacherClazzRequest
     * @param response
     */
    @PostMapping("/delete")
    public void deleteCourseTeacherClazz(@RequestBody DeleteCourseTeacherClazzRequest deleteCourseTeacherClazzRequest,
            HttpServletResponse response) {
        Long courseTeacherClazzId = TransitionUtil.stringToLong(deleteCourseTeacherClazzRequest.getCourseTeacherClazzId());
        String deleteMessage = courseTeacherClazzService.deleteCourseTeacherClazz(courseTeacherClazzId);
        PrintWriterUtil.print(deleteMessage, response);
    }
}
