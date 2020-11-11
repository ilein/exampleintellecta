package edu.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebServiceLogger {

    private static Logger LOG = LoggerFactory.getLogger(WebServiceLogger.class);

    @Pointcut(value = "execution(public * edu.example.service.AuthorService.*(..))")
    public void serviceAuthorMethod() {}

    @Pointcut(value = "execution(public * edu.example.service.BookService.*(..))")
    public void serviceBookMethod() {}

    @Pointcut("@annotation(edu.example.annotation.Loggable)")
    public void loggableMethod() {}

    @Pointcut("@annotation(edu.example.annotation.LoggableReturning)")
    public void loggableReturningMethod() { }

    /*
    @Around(value = "serviceMethod() && loggableMethod()")
    public Object logWebServiceCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();
        LOG.info("Call method " + methodName + " with args " + Arrays.toString(methodArgs));
        Object result = thisJoinPoint.proceed();
        LOG.info("Method " + methodName + " returns " + result);
        return result;
    }
    */

    @Before(value = "(serviceAuthorMethod() || serviceBookMethod()) && loggableMethod()")
    public void logWebServiceBefore (JoinPoint jp) throws Throwable {
        String methodName = jp.getSignature().getName();
        Object[] methodArgs = jp.getArgs();
        LOG.info("Before method " + methodName + " with args " + Arrays.toString(methodArgs));
    }

    @After(value = "(serviceAuthorMethod() || serviceBookMethod()) && loggableMethod()")
    public void logWebServiceAfter (JoinPoint jp) throws Throwable {
        String methodName = jp.getSignature().getName();
        LOG.info("After method " + methodName);
    }

    @AfterReturning(pointcut="loggableReturningMethod()", returning="retVal")
    public void logWebServiceAfterReturning(Object retVal) {
        LOG.info("AfterReturning " + retVal.toString());
    }
}
