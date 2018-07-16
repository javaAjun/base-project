package com.palmble.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.palmble.enums.LogEnum;


/**
* <p>Title: 自定义注解拦截Controller</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年7月3日 
* @version 1.0
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomLog {
	/* 记录到不同的日志表 */
	LogEnum logEnum() default LogEnum.PUBLIC;

	/* 模块名称：会员管理，交易管理、系统管理 */
	String module() default "";

	/* 方法描述：会员列表、登录信息*/
	String desc() default "";
}
