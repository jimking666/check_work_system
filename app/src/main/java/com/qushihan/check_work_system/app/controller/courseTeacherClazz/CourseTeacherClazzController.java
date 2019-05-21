package com.qushihan.check_work_system.app.controller.courseTeacherClazz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qushihan.check_work_system.core.dto.CourseTeacherClazzDto;
import com.qushihan.check_work_system.core.dto.GetCourseTeacherClazzBySearchRequest;
import com.qushihan.check_work_system.course.api.CourseService;
import com.qushihan.check_work_system.course.dto.CourseDto;
import com.qushihan.check_work_system.teacher.dto.TeacherDto;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courseTeacherClazz")
public class CourseTeacherClazzController {

    @Autowired
    private CourseTeacherClazzService courseTeacherClazzService;

    @Autowired
    private CourseService courseService;

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

    /**
     * 通过课程名称查询课程教师班级
     *
     * @param getCourseTeacherClazzBySearchRequest
     * @param request
     * @param response
     */
    @RequestMapping("/getCourseTeacherClazzBySearch")
    public void getCourseTeacherClazzBySearch(@RequestBody GetCourseTeacherClazzBySearchRequest getCourseTeacherClazzBySearchRequest, HttpServletRequest request, HttpServletResponse response) {
        String searchCourseName = getCourseTeacherClazzBySearchRequest.getSearchCourseName();
        List<CourseDto> courseDtos = courseService.getBySearchCourseName(searchCourseName);
        List<Long> courseIds = courseDtos.stream()
                .map(CourseDto::getCourseId)
                .collect(Collectors.toList());
        TeacherDto teacherDto = (TeacherDto) request.getServletContext().getAttribute("teacherDto");
        List<CourseTeacherClazzDto> courseTeacherClazzDtos = courseTeacherClazzService.getByTeacherId(teacherDto.getTeacherId());
        courseTeacherClazzDtos = courseTeacherClazzDtos.stream()
                .filter(courseTeacherClazzDto -> courseIds.contains(courseTeacherClazzDto.getCourseId()))
                .collect(Collectors.toList());
        request.getServletContext().setAttribute("courseTeacherClazzDtos", courseTeacherClazzDtos);
        PrintWriterUtil.print("查询成功", response);
    }
}
