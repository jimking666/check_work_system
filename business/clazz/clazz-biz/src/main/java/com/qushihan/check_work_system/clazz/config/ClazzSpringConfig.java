package com.qushihan.check_work_system.clazz.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.qushihan.check_work_system.clazz.api.impl.ClazzApiPkg;
import com.qushihan.check_work_system.clazz.biz.service.ClazzServiceBizPkg;
import com.qushihan.check_work_system.clazz.dao.ClazzDaoPkg;
import com.qushihan.check_work_system.inf.config.InfSpringConfig;

@Configuration
@Import(value = { InfSpringConfig.class })
@ComponentScan(basePackageClasses = { ClazzApiPkg.class, ClazzServiceBizPkg.class, ClazzDaoPkg.class })
@MapperScan(basePackages = {"com.qushihan.check_work_system.clazz.mapper"}, sqlSessionFactoryRef =
        "clazzSqlSessionFactoryBean")
public class ClazzSpringConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory clazzSqlSessionFactoryBean() throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.qushihan.check_work_system.clazz.model");
        return sessionFactoryBean.getObject();
    }
}
