package com.qushihan.check_work_system.app.controller.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qushihan.check_work_system.course.dto.CourseDto;
import com.qushihan.check_work_system.course.dto.GetCourseBySearchRequest;
import com.qushihan.check_work_system.course.enums.CreateCourseStatus;
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

import java.util.List;
import java.util.stream.Collectors;

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
    public void createCourse(@RequestBody CreateCourseRequest createCourseRequest, HttpServletRequest request, HttpServletResponse response) {
        String courseName = createCourseRequest.getCourseName();
        String createMessge = courseService.createCourse(courseName);
        if (createMessge.equals(CreateCourseStatus.CREATE_SUCCESS.getMessage())) {
            List<CourseDto> courseDtos = courseService.queryAllCourse();
            request.getServletContext().setAttribute("courseDtos", courseDtos);
            request.getServletContext().setAttribute("searchCourseDtos", courseDtos);
        }
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

    /**
     * 通过课程名称查询课程
     *
     * @param getCourseBySearchRequest
     * @param request
     * @param response
     */
    @RequestMapping("/getCourseBySearch")
    public void getCourseBySearch(@RequestBody GetCourseBySearchRequest getCourseBySearchRequest, HttpServletRequest request, HttpServletResponse response) {
        String searchCourseName = getCourseBySearchRequest.getSearchCourseName();
        List<CourseDto> searchCourseDtos = courseService.getBySearchCourseName(searchCourseName);
        List<Long> courseIds = searchCourseDtos.stream()
                .map(CourseDto::getCourseId)
                .collect(Collectors.toList());
        List<CourseDto> courseDtos = courseService.queryAllCourse();
        courseDtos = courseDtos.stream()
                .filter(courseDto -> courseIds.contains(courseDto.getCourseId()))
                .collect(Collectors.toList());
        request.getServletContext().setAttribute("searchCourseDtos", courseDtos);
        PrintWriterUtil.print("查询成功", response);
    }
}
