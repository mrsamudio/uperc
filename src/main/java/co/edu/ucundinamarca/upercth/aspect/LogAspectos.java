package co.edu.ucundinamarca.upercth.aspect;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//@Aspect("perthis(co.edu.ucundinamarca.upercth.web.ctrls.UsuarioCtrl()")
@Aspect
@Component
public class LogAspectos {

//	private static Logger logger = LoggerFactory.getLogger(LogAspectos.class);
	private Logger logger = LogManager.getLogger(LogAspectos.class); // LoggerFactory.getLogger(getClass());
	private static Logger applicationsfile = LogManager.getLogger("applicationsfile");
	/**
	 * Punto de corte para todos los métodos públicos
	 */
	@Pointcut("execution( public * co.edu.ucundinamarca.upercth.*.*(..))")
    private void metodosPublicos() {} 

	/**
	 * 
	 */
    @Pointcut("within (co.edu.ucundinamarca.upercth..*)")
    private void enUperc() {}    

    /**
     * 
     */
    @Pointcut("metodosPublicos() && enUperc()")
    private void metodosPublicosEnUperc() {}
	
    /**
     * 
     * @param joinPoint
     */
	@Before("within (@org.springframework.stereotype.Controller *)")
	public void logAntes(JoinPoint joinPoint) {
		logger.info("Antes de ejecutar " + joinPoint.getSignature().getName());
		applicationsfile.info("Antes de ejecutar " + joinPoint.getSignature().getName());
	}


	/**
	 * 
	 * @param joinPoint
	 */
	@After("within (@org.springframework.stereotype.Controller *)")
	public void logDespues(JoinPoint joinPoint) {
		logger.info("Despues de ejecutar " + joinPoint.getSignature().getName());
		applicationsfile.info("Despues de ejecutar " + joinPoint.getSignature().getName());
	}
	
	/**
	 * Registra el
	 * @param joinPoint
	 * @param resultado
	 */
	@AfterReturning(pointcut = "metodosPublicosEnUperc()", returning = "resultado")
	public void logDespuesRes(JoinPoint joinPoint, Object resultado) {
		
		logger.info("En el método " + joinPoint.getSignature().getName());
		logger.info("El valor retornado es: " + resultado.toString());
		
	}
	
	
	
//	Módulo de usuarios
	
	/**
	 * Punto de corte para todos los métodos públicos en el módulo usuarios
	 */
	@Pointcut("execution( public * co.edu.ucundinamarca.upercth.web.ctrls.*.*(..))")
    private void puntoDeCorteUsuarios() {} 	

	/**
	 * 
	 */
    @Pointcut("within (co.edu.ucundinamarca.upercth.web.crtls..*)")
    private void enUsuarios() {}    

    /**
     * Unión de dos puntos de corte
     */
    @Pointcut("puntoDeCorteUsuarios() && enUsuarios()")
    private void enUsuariosPublicos() {}

    
	/**
	 * Registra el retorno de los sucesos en el módulo usuarios
	 * @param joinPoint
	 * @param resultado
	 */
	@AfterReturning(pointcut = "enUsuariosPublicos()", returning = "resultado")
	public void logUsuariosRetorno(JoinPoint joinPoint, Object resultado) {
		logger.info("En el método " + joinPoint.getSignature().getName());
		applicationsfile.info("En el método " + joinPoint.getSignature().getName());
		logger.info("El valor retornado en usuarios es: " + resultado.toString());
		
	}
	
	
//	public LogAspectos() {
//		// TODO Auto-generated constructor stub
//	}

//	/**
//	 *
//	 * Pointcut that matches all repositories, services and Web REST endpoints.
//	 */
//	@Pointcut("within(@org.springframework.stereotype.Repository *)"
//			+ " || within(@org.springframework.stereotype.Service *)"
//			+ " || within(@org.springframework.stereotype.Controller *)" +
////       " || within(@org.springframework.web.bind.annotation.RestController *)" +
//			"")
//	public void springBeanPointcut() {
//		// Method is empty as this is just a Pointcut, the implementations are in the
//		// advices.
//	}
//
//	
////   @Pointcut("within(net.guides.springboot2.springboot2jpacrudexample..*)" +
////       " || within(net.guides.springboot2.springboot2jpacrudexample.service..*)" +
////       " || within(net.guides.springboot2.springboot2jpacrudexample.controller..*)" +
//
//	/**
//	 * Pointcut that matches all Spring beans in the application's main packages.
//	 */
//	@Pointcut("within(co.edu.ucundinamarca.upercth..*)" + 
//		" || within(co.edu.ucundinamarca.upercth.model.services..*)" +
//		" || within(co.edu.ucundinamarca.upercth.web.ctrls..*)" + 
//		"")
//	public void applicationPackagePointcut() {
//		// Method is empty as this is just a Pointcut, the implementations are in the
//		// advices.
//	}
//
//	/**
//	 * Advice that logs methods throwing exceptions.
//	 *
//	 * @param joinPoint join point for advice
//	 * @param e         exception
//	 */
//	@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
//	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
//		logger.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
//				joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
//	}
//
//	/**
//	 * Advice that logs when a method is entered and exited.
//	 *
//	 * @param joinPoint join point for advice
//	 * @return result
//	 * @throws Throwable throws IllegalArgumentException
//	 */
//	@Around("applicationPackagePointcut() && springBeanPointcut()")
//	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//		if (logger.isDebugEnabled()) {
//			logger.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
//					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
//		}
//		try {
//			Object result = joinPoint.proceed();
//			if (logger.isDebugEnabled()) {
//				logger.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
//						joinPoint.getSignature().getName(), result);
//			}
//			return result;
//		} catch (IllegalArgumentException e) {
//			logger.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
//					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
//			throw e;
//		}
//	}

}
