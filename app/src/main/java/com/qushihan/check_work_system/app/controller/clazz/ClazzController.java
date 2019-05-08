package com.qushihan.check_work_system.app.controller.clazz;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qushihan.check_work_system.app.util.PrintWriterUtil;
import com.qushihan.check_work_system.clazz.api.ClazzService;
import com.qushihan.check_work_system.clazz.dto.CreateClazzRequest;
import com.qushihan.check_work_system.clazz.dto.DeleteClazzRequest;
import com.qushihan.check_work_system.inf.util.TransitionUtil;

@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 创建班级
     *
     * @param createClazzRequest
     * @param response
     */
    @PostMapping("/create")
    public void createClazz(@RequestBody CreateClazzRequest createClazzRequest, HttpServletResponse response) {
        String clazzName = createClazzRequest.getClazzName();
        String createMessge = clazzService.createClazz(clazzName);
        PrintWriterUtil.print(createMessge, response);
    }

    /**
     * 删除班级
     *
     * @param deleteClazzRequest
     * @param response
     */
    @PostMapping("/delete")
    public void deleteClazz(@RequestBody DeleteClazzRequest deleteClazzRequest, HttpServletResponse response) {
        Long clazzId = TransitionUtil.stringToLong(deleteClazzRequest.getClazzId());
        String deleteMessge = clazzService.deleteClazz(clazzId);
        PrintWriterUtil.print(deleteMessge, response);
    }
}
