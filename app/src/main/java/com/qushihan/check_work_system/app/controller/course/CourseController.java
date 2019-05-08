package com.qushihan.check_work_system.app.controller.course;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qushihan.check_work_system.app.util.PrintWriterUtil;
import com.qushihan.check_work_system.course.api.CourseService;
import com.qushihan.check_work_system.course.dto.CreateCourseRequest;
import com.qushihan.check_work_system.course.dto.DeleteCourseRequest;
import com.qushihan.check_work_system.inf.util.TransitionUtil;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 创建课程
     *
     * @param createCourseRequest
     * @param response
     */
    @PostMapping("/create")
    public void createCourse(@RequestBody CreateCourseRequest createCourseRequest, HttpServletResponse response) {
        String courseName = createCourseRequest.getCourseName();
        String createMessge = courseService.createCourse(courseName);
        PrintWriterUtil.print(createMessge, response);
    }

    /**
     * 删除课程
     *
     * @param deleteCourseRequest
     * @param response
     */
    @PostMapping("/delete")
    public void deleteCourse(@RequestBody DeleteCourseRequest deleteCourseRequest, HttpServletResponse response) {
        Long courseId = TransitionUtil.stringToLong(deleteCourseRequest.getCourseId());
        String deleteMessge = courseService.deleteCourse(courseId);
        PrintWriterUtil.print(deleteMessge, response);
    }
}
