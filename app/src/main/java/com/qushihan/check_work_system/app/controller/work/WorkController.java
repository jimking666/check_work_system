package com.qushihan.check_work_system.app.controller.work;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qushihan.check_work_system.app.util.PrintWriterUtil;
import com.qushihan.check_work_system.inf.util.TransitionUtil;
import com.qushihan.check_work_system.work.api.WorkService;
import com.qushihan.check_work_system.work.dto.CreateWorkRequest;
import com.qushihan.check_work_system.work.dto.DeleteWorkRequest;
import com.qushihan.check_work_system.work.dto.ReleaseWorkDetailRequest;
import com.qushihan.check_work_system.work.dto.WorkDto;

@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkService workService;

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
    public void createWork(@RequestBody CreateWorkRequest createWorkRequest, HttpServletResponse response) {
        String workTitle = createWorkRequest.getWorkTitle();
        String workContent = createWorkRequest.getWorkContent();
        Float repetitiveRate = createWorkRequest.getRepetitiveRate();
        Long courseTeacherClazzId = TransitionUtil.stringToLong(createWorkRequest.getCourseTeacherClazzId());
        String createMessge = workService.createWork(workTitle, workContent, repetitiveRate, courseTeacherClazzId);
        PrintWriterUtil.print(createMessge, response);
    }

    /**
     * 删除作业
     *
     * @param deleteWorkRequest
     * @param response
     */
    @PostMapping("/delete")
    public void deleteWork(@RequestBody DeleteWorkRequest deleteWorkRequest, HttpServletResponse response) {
        Long workId = TransitionUtil.stringToLong(deleteWorkRequest.getWorkId());
        String deleteMessge = workService.deleteWork(workId);
        PrintWriterUtil.print(deleteMessge, response);
    }
}
