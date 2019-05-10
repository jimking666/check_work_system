package com.qushihan.check_work_system.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.qushihan.check_work_system.app.controller.config.WebMvcConfig;
import com.qushihan.check_work_system.clazz.config.ClazzSpringConfig;
import com.qushihan.check_work_system.core.config.CoreSpringConfig;
import com.qushihan.check_work_system.course.config.CourseSpringConfig;
import com.qushihan.check_work_system.inf.config.InfSpringConfig;
import com.qushihan.check_work_system.student.config.StudentSpringConfig;
import com.qushihan.check_work_system.submitwork.config.SubmitWorkSpringConfig;
import com.qushihan.check_work_system.teacher.config.TeacherSpringConfig;
import com.qushihan.check_work_system.work.config.WorkSpringConfig;

@SpringBootApplication
@Import(value = { WebMvcConfig.class, CoreSpringConfig.class, CourseSpringConfig.class, ClazzSpringConfig.class,
        TeacherSpringConfig.class, StudentSpringConfig.class, InfSpringConfig.class, WorkSpringConfig.class,
        SubmitWorkSpringConfig.class })
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}

