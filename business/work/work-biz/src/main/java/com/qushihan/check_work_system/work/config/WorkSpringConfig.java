package com.qushihan.check_work_system.work.config;

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
import com.qushihan.check_work_system.work.api.impl.WorkApiPkg;
import com.qushihan.check_work_system.work.biz.service.WorkServiceBizPkg;
import com.qushihan.check_work_system.work.dao.WorkDaoPkg;

@Configuration
@Import(value = { InfSpringConfig.class })
@ComponentScan(basePackageClasses = { WorkApiPkg.class, WorkServiceBizPkg.class, WorkDaoPkg.class })
@MapperScan(basePackages = {"com.qushihan.check_work_system.work.mapper"}, sqlSessionFactoryRef =
        "workSqlSessionFactoryBean")
public class WorkSpringConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory workSqlSessionFactoryBean() throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.qushihan.check_work_system.work.model");
        return sessionFactoryBean.getObject();
    }
}
