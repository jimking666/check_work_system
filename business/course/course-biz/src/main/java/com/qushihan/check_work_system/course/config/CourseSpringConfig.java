package com.qushihan.check_work_system.course.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.qushihan.check_work_system.course.api.impl.CourseApiPkg;
import com.qushihan.check_work_system.course.biz.service.CourseServiceBizPkg;
import com.qushihan.check_work_system.course.dao.CourseDaoPkg;
import com.qushihan.check_work_system.inf.config.InfSpringConfig;


@Configuration
@Import(value = { InfSpringConfig.class })
@ComponentScan(basePackageClasses = { CourseApiPkg.class, CourseServiceBizPkg.class, CourseDaoPkg.class })
@MapperScan(basePackages = {"com.qushihan.check_work_system.course.mapper"}, sqlSessionFactoryRef =
        "courseSqlSessionFactoryBean")
public class CourseSpringConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory courseSqlSessionFactoryBean() throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.qushihan.check_work_system.course.model");
        return sessionFactoryBean.getObject();
    }
}
