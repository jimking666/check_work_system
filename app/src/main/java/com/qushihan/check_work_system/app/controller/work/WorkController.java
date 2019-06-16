package com.qushihan.check_work_system.app.controller.work;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qushihan.check_work_system.core.api.CourseTeacherClazzService;
import com.qushihan.check_work_system.core.dto.CourseTeacherClazzDto;
import com.qushihan.check_work_system.teacher.dto.TeacherDto;
import com.qushihan.check_work_system.work.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qushihan.check_work_system.app.util.PrintWriterUtil;
import com.qushihan.check_work_system.inf.util.TransitionUtil;
import com.qushihan.check_work_system.work.api.WorkService;

@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private CourseTeacherClazzService courseTeacherClazzService;
    /**
     * 发布作业详情
     *
     * @param releaseWorkDetailRequest
     * @param request
     * @param response
     */
    @PostMapping("/releaseWorkDetail")
    public void releaseWorkDetail(@RequestBody ReleaseWorkDetailRequest releaseWorkDetailRequest,
            HttpServletRequest request, HttpServletResponse response) {
        Long courseTeacherClazzId = TransitionUtil.stringToLong(releaseWorkDetailRequest.getCourseTeacherClazzId());
        List<WorkDto> workDtos = workService.queryWorkDtoListByCourseTeacherClazzId(courseTeacherClazzId);
        request.getServletContext().setAttribute("workDtos", workDtos);
        request.getServletContext().setAttribute("courseTeacherClazzId", courseTeacherClazzId);
        PrintWriterUtil.print("发布作业详情查询成功", response);
    }

    /**
     * 发布作业
     *
     * @param createWorkRequest
     * @param response
     */
    @PostMapping("/create")
    public void createWork(@RequestBody CreateWorkRequest createWorkRequest, HttpServletRequest request, HttpServletResponse response) {
        String workTitle = createWorkRequest.getWorkTitle();
        String workContent = createWorkRequest.getWorkContent();
        Float repetitiveRate = createWorkRequest.getRepetitiveRate();
        Long courseTeacherClazzId = TransitionUtil.stringToLong(createWorkRequest.getCourseTeacherClazzId());
        String createMessge = workService.createWork(workTitle, workContent, repetitiveRate, courseTeacherClazzId);
        TeacherDto teacherDto = (TeacherDto) request.getServletContext().getAttribute("teacherDto");
        List<CourseTeacherClazzDto> courseTeacherClazzDtos = courseTeacherClazzService.getByTeacherId(teacherDto.getTeacherId());
        List<WorkDto> workDtos = workService.queryWorkDtoListByCourseTeacherClazzId(courseTeacherClazzId);
        request.getServletContext().setAttribute("courseTeacherClazzDtos", courseTeacherClazzDtos);
        request.getServletContext().setAttribute("workDtos", workDtos);
        PrintWriterUtil.print(createMessge, response);
    }

    /**
     * 删除作业
     *
     * @param deleteWorkRequest
     * @param response
     */
    @PostMapping("/delete")
    public void deleteWork(@RequestBody DeleteWorkRequest deleteWorkRequest, HttpServletRequest request ,HttpServletResponse response) {
        Long workId = TransitionUtil.stringToLong(deleteWorkRequest.getWorkId());
        Long courseTeacherClazzId = (Long) request.getServletContext().getAttribute("courseTeacherClazzId");
        String deleteMessge = workService.deleteWork(workId, courseTeacherClazzId);
        PrintWriterUtil.print(deleteMessge, response);
    }

    /**
     * 通过作业题目名称查询作业
     *
     * @param getWorkBySearchRequest
     * @param request
     * @param response
     */
    @RequestMapping("/getWorkBySearch")
    public void getWorkBySearch(@RequestBody GetWorkBySearchRequest getWorkBySearchRequest, HttpServletRequest request, HttpServletResponse response) {
        String searchWorkTitle = getWorkBySearchRequest.getSearchWorkTitle();
        List<WorkDto> searchWorkDtos = workService.getBySearchWorkTitle(searchWorkTitle);
        List<Long> workIds = searchWorkDtos.stream()
                .map(WorkDto::getWorkId)
                .collect(Collectors.toList());
        Long courseTeacherClazzId = (Long) request.getServletContext().getAttribute("courseTeacherClazzId");
        List<WorkDto> workDtos = workService.queryWorkDtoListByCourseTeacherClazzId(courseTeacherClazzId);
        workDtos = workDtos.stream()
                .filter(workDto -> workIds.contains(workDto.getWorkId()))
                .collect(Collectors.toList());
        request.getServletContext().setAttribute("workDtos", workDtos);
        PrintWriterUtil.print("查询成功", response);
    }
}
