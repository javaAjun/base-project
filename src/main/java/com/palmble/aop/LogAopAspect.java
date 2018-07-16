package com.palmble.aop;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.palmble.annotation.CustomLog;
import com.palmble.base.PalmbleBaseService;


/**
 * [切点类，用于公共日志记录] 
 * 【前置通知[Before advice]】：在连接点前面执行，前置通知不会影响连接点的执行，除非此处抛出异常。 
 * 【正常返回通知[After returning advice]】：在连接点正常执行完成后执行，如果连接点抛出异常，则不会执行。 
 * 【异常返回通知[After throwing advice]】：在连接点抛出异常后执行。 
 * 【返回通知[After (finally) advice]】：在连接点执行完成后执行，不管是正常执行完成，还是抛出异常，都会执行返回通知中的内容。 
 * 【环绕通知[Around advice]】：环绕通知围绕在连接点前后 ，比如一个方法调用的前后。这是最强大的通知类型，能在方法调用前后自定义一些操作。环绕通知还需要负责决定是继续处理joinPoint(调用ProceedingJoinPoint的proceed方法)还是中断执行。
 * @author WangYanke  
 * @date 2018年7月3日 
 * @version 1.0
 */
@Aspect
@Component
public class LogAopAspect {
	@Autowired
	private PalmbleBaseService baseService;
	/* 本地异常日志记录对象 */
	private static final Logger logger = LoggerFactory
			.getLogger(LogAopAspect.class);
	/* Controller层切点 */
	
	@Pointcut("execution(* com.palmble.controller.*.*(..))")
	public void controllerAspect() {
		System.out.println("我是一个Controller切入点");
	}
	
	/* Service层切点 */
	@Pointcut("execution(* com.palmble.controller.*.*(..))")
	public void serviceAspect() {
		System.out.println("我是一个Service切入点");
	}
	
	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@AfterReturning(value="controllerAspect()")
	public void doBeforeAdvice(JoinPoint joinPoint) {
		System.out.println("执行结束后执行");
		saveLog(joinPoint, null);
	}
	
	/**
	 * 
	 * [异常通知 用于拦截service层记录异常日志] <br>
	 * 
	 * @author WangYanke  <br>
	 * @date 2018-7-03 下午5:45:12 <br>
	 * @param joinPoint
	 *            切点
	 * @param e
	 *            异常 <br>
	 */
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable e) {
		this.saveLog(joinPoint, e);
	}
	
	/**
	 * 
	 * [按照模块日志保存] <br>
	 * 
	 * @author Mr.Liboary <br>
	 * @date 2017-7-13 下午5:45:12 <br>
	 * @param joinPoint
	 *            切点
	 * @param e
	 *            异常 <br>
	 */
	private void saveLog(JoinPoint joinPoint, Throwable e) {
		try {
			/* 获取自定义日志注解对象 */
			String module = getMethodModule(joinPoint);
			CustomLog customLog = getCustomLog2(joinPoint);
			if(customLog==null){
				return;
			}
			String desc = getMethodDesc(joinPoint);
			String method = joinPoint.getTarget().getClass().getName() + "."
					+ joinPoint.getSignature().getName() + "()";
			String params = "";
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					// TODO 需要把joinPoin.getArgs()[i]转化为JSON字符串
					params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
				}
			}
			// *========控制台输出=========*//
			System.out.println("=====通知开始=====");
			System.out.println("请求方法:" + method);
			System.out.println("模块名称:" + module + "--" + customLog.module());
			System.out.println("方法描述:" + desc + "--" + customLog.desc());
			String changeReason = "正常";
			if (e != null) {
				changeReason = "程序异常";
				System.out.println("异常代码:" + e.getClass().getName());
				System.out.println("异常信息:" + e.getMessage());
			}
			System.out.println("=====通知结束=====");
			baseService.saveLog(customLog.logEnum(), 0, module, desc, method,
					params, changeReason, e);
		} catch (Exception ex) {
			ex.printStackTrace();
			// 记录本地异常日志
			logger.error("==记录日志异常==");
			logger.error("异常信息:{}", ex.getMessage());
		}
	}
	
	
	/**
	 * 获取注解中对方法的模块信息
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private static String getMethodModule(JoinPoint joinPoint) throws Exception {
		/* 拦截的实体类名字，就是当前正在执行的类 */
		String targetName = joinPoint.getTarget().getClass().getName();
		/* 拦截的方法名称。当前正在执行的方法 */
		String methodName = joinPoint.getSignature().getName();
		/* 拦截的方法参数 */
		Object[] args = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String module = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == args.length) {
					if(method.getAnnotation(CustomLog.class)==null){
						continue;
					}
					module = method.getAnnotation(CustomLog.class).module();
					break;
				}
			}
		}
		return module;
	}
	
	/**
	 * 获取注解中对方法的描述信息
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private static String getMethodDesc(JoinPoint joinPoint) throws Exception {
		/* 拦截的实体类名字，就是当前正在执行的类 */
		String targetName = joinPoint.getTarget().getClass().getName();
		/* 拦截的方法名称。当前正在执行的方法 */
		String methodName = joinPoint.getSignature().getName();
		/* 拦截的方法参数 */
		Object[] args = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == args.length) {
					if(method.getAnnotation(CustomLog.class)==null){
						continue;
					}
					description = method.getAnnotation(CustomLog.class).desc();
					break;
				}
			}
		}
		return description;
	}
	
	/**
	 * 
	 * [获取自定义日志注解对象] <br>
	 * 
	 * @deprecated 请使用getCustomLog方法 <br>
	 * @author WangYanke  <br>
	 * @date 2018-7-03 下午5:45:12 <br>
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 * <br>
	 */
	@Deprecated
	@SuppressWarnings({ "rawtypes", "unused" })
	private static CustomLog getCustomLog2(JoinPoint joinPoint)
			throws Exception {
		/* 拦截的实体类名字，就是当前正在执行的类 */
		String targetName = joinPoint.getTarget().getClass().getName();
		/* 拦截的方法名称。当前正在执行的方法 */
		String methodName = joinPoint.getSignature().getName();
		/* 拦截的方法参数 */
		Object[] args = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		CustomLog customLog = null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == args.length) {
					customLog = method.getAnnotation(CustomLog.class);
					return customLog;
				}
			}
		}
		return customLog;
	}
}
