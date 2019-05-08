package com.qushihan.check_work_system.core.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.qushihan.check_work_system.core.api.impl.CoreApiPkg;
import com.qushihan.check_work_system.core.biz.service.CoreServiceBizPkg;
import com.qushihan.check_work_system.core.dao.CoreDaoPkg;
import com.qushihan.check_work_system.inf.config.InfSpringConfig;

@Configuration
@Import(value = { InfSpringConfig.class })
@ComponentScan(basePackageClasses = { CoreApiPkg.class, CoreServiceBizPkg.class, CoreDaoPkg.class })
@MapperScan(basePackages = { "com.qushihan.check_work_system.core.mapper" }, sqlSessionFactoryRef = "coreSqlSessionFactoryBean")
public class CoreSpringConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory coreSqlSessionFactoryBean(@Autowired DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.qushihan.check_work_system.core.model");
        return sessionFactoryBean.getObject();
    }
}
