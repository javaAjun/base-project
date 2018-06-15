package com.palmble.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;

public class MyBatisConfig {
	 @Bean(name = "sqlSessionFactory")
	    public SqlSessionFactory sqlSessionFactoryBean() {
	    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	//  bean.setDataSource(dataSource());

	    bean.setTypeAliasesPackage("com.kx.springboot.bean");
		return null;
	 }
}
