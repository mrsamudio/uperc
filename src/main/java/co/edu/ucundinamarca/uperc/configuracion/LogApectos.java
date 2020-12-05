package co.edu.ucundinamarca.uperc;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mrsamudio
 *
 */
@Aspect
@Component
public class LogApectos {
	
	private static Logger logger = LogManager.getLogger(LogApectos.class);
	
	//TODO completar aspectos
	@Before("execution(* co.edu.ucundinamarca.uperc.*(..))")
	public void logAntes(JoinPoint joinPoint) {
		logger.info("" + joinPoint.getSignature().getName());
	}

	//TODO completar aspectos
	@After("execution(* co.edu.ucundinamarca.uperc.*(..))")
	public void logDespues(JoinPoint joinPoint) {
		logger.info("" + joinPoint.getSignature().getName());
	}
	
	@AfterThrowing
	public void logAAfterThrowging(JoinPoint joinPoint, Throwable excepcion) {
		
	}
	
	@Around("execution(* co.edu.ucundinamarca.uperc.*(..))")
	public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		
	}
	
}
