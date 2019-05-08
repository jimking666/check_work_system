package com.qushihan.check_work_system.student.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.qushihan.check_work_system.inf.config.InfSpringConfig;
import com.qushihan.check_work_system.student.api.impl.StudentApiPkg;
import com.qushihan.check_work_system.student.biz.service.StudentServiceBizPkg;
import com.qushihan.check_work_system.student.dao.StudentDaoPkg;

@Configuration
@Import(value = { InfSpringConfig.class })
@ComponentScan(basePackageClasses = { StudentApiPkg.class, StudentServiceBizPkg.class, StudentDaoPkg.class })
@MapperScan(basePackages = {"com.qushihan.check_work_system.student.mapper"}, sqlSessionFactoryRef =
        "studentSqlSessionFactoryBean")
public class StudentSpringConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory studentSqlSessionFactoryBean() throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.qushihan.check_work_system.student.model");
        return sessionFactoryBean.getObject();
    }
}
