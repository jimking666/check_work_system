package com.qushihan.check_work_system.app.controller.submitwork;

import java.util.Arrays;
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
import com.qushihan.check_work_system.submitwork.api.SubmitWorkService;
import com.qushihan.check_work_system.submitwork.dto.QuerySubmitWorkContentAndScoreRequest;
import com.qushihan.check_work_system.submitwork.dto.SaveScoreRequest;
import com.qushihan.check_work_system.submitwork.dto.SubmitWorkDetailRequest;
import com.qushihan.check_work_system.submitwork.dto.SubmitWorkDto;

@RestController
@RequestMapping("/submitWork")
public class SubmitWorkController {

    @Autowired
    private SubmitWorkService submitWorkService;

    /**
     * 提交作业详情
     *
     * @param submitWorkDetailRequest
     * @param request
     * @param response
     */
    @PostMapping("/submitWorkDetail")
    public void releaseWorkDetail(@RequestBody SubmitWorkDetailRequest submitWorkDetailRequest,
            HttpServletRequest request, HttpServletResponse response) {
        Long workId = TransitionUtil.stringToLong(submitWorkDetailRequest.getWorkId());
        List<SubmitWorkDto> submitWorkDtos = submitWorkService.querySubmitWorkDtoListByWorkId(workId);
        request.getServletContext().setAttribute("submitWorkDtos", submitWorkDtos);
        request.getServletContext().setAttribute("workId", workId);
        PrintWriterUtil.print("提交作业详情查询成功", response);
    }

    /**
     * 查询提交作业内容与分数
     * @param querySubmitWorkContentAndScoreRequest
     * @param response
     */
    @PostMapping("/querySubmitWorkContentAndScore")
    public void querySubmitWorkContentAndScore(@RequestBody QuerySubmitWorkContentAndScoreRequest querySubmitWorkContentAndScoreRequest, HttpServletResponse response) {
        Long submitWorkId = TransitionUtil.stringToLong(querySubmitWorkContentAndScoreRequest.getSubmitWorkId());
        String submitWorkContentAndScoreMessage = submitWorkService.queryContentAndScoreBySubmitWorkIds(submitWorkId);
        PrintWriterUtil.print(submitWorkContentAndScoreMessage, response);
    }

    /**
     * 保存分数
     * @param saveScoreRequest
     * @param response
     */
    @PostMapping("/saveScore")
    public void saveScore(@RequestBody SaveScoreRequest saveScoreRequest, HttpServletResponse response) {
        Long submitWorkId = TransitionUtil.stringToLong(saveScoreRequest.getSubmitWorkId());
        String[] scores = saveScoreRequest.getScore().split(" ");
        if (scores.length > 1) {
            PrintWriterUtil.print("格式错误", response);
        } else {
            Integer score = Integer.parseInt(saveScoreRequest.getScore().trim());
            String saveScoreMessage = submitWorkService.saveScoreBySubmitWorkId(submitWorkId, score);
            PrintWriterUtil.print(saveScoreMessage, response);
        }
    }
}
