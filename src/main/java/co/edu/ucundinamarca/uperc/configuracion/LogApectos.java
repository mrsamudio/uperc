package co.edu.ucundinamarca.uperc.configuracion;


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
import org.springframework.stereotype.Component;

/**
 * 
 * @author mrsamudio
 *
 */
@Component
@Aspect
public class LogApectos {
	
	private static Logger logger = LogManager.getLogger(LogApectos.class);
	
	/**
	 * Punto de corte para todos los métodos públicos
	 */
	@Pointcut("execution( public * co.edu.ucundinamarca.uperc.*.*(..))")
    private void metodosPublicos() {} 	

	/**
	 * 
	 */
    @Pointcut("within (co.edu.ucundinamarca.uperc..*)")
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
	}


	/**
	 * 
	 * @param joinPoint
	 */
	@After("within (@org.springframework.stereotype.Controller *)")
	public void logDespues(JoinPoint joinPoint) {
		logger.info("Despues de ejecutar " + joinPoint.getSignature().getName());
	}
	
//	@AfterThrowing("execution(* *.*(..))")
//	public void logAAfterThrowging(JoinPoint joinPoint, Throwable e) {
//		logger.info("al ejecutar " + joinPoint.getSignature().getName());
//		logger.info("Se ha lanzado la excepción: " + e.getMessage());
//	}
	
//	@Around("execution(* co.edu.ucundinamarca.uperc.*(..))")
//	public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//		logger.info("Antes y despues @Around");
//		logger.info("Antes de ejecutar " + joinPoint.getSignature().getName());
//		joinPoint.proceed();
//		logger.info("Despues de ejecutar " + joinPoint.getSignature().getName());
//		
//	}
	
	/**
	 * Registra el
	 * @param joinPoint
	 * @param resultado
	 */
	@AfterReturning(pointcut = "metodosPublicos()", returning = "resultado")
	public void logDespuesRes(JoinPoint joinPoint, Object resultado) {
		
		logger.info("En el método " + joinPoint.getSignature().getName());
		logger.info("El valor retornado es: " + resultado.toString());
		
	}
	
}