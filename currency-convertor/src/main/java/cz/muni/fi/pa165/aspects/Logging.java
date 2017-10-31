package cz.muni.fi.pa165.aspects;
 
 import org.aspectj.lang.ProceedingJoinPoint;
 import org.aspectj.lang.annotation.Around;
 import org.aspectj.lang.annotation.Aspect;
 
 import javax.inject.Named;
  
 /**
  * @author Miroslav Kubus
  */
@Named
@Aspect
public class Logging {

    @Around("execution(public * *(..))")
    public Object logMethodTime(ProceedingJoinPoint joinPoint) throws Throwable {
 
         Long startTime = System.currentTimeMillis();
         Object result = joinPoint.proceed();
         Long endTime = System.currentTimeMillis();
         
         Long executionTime = endTime - startTime;
 
         System.err.println("Execution of " + joinPoint.getSignature() + " takes " + executionTime + "ms.");
 
         return result;
     }
 }