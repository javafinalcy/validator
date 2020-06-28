package com.workit.validator.aop;


import com.workit.validator.comm.ReflectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VerificationAspect {


    @Pointcut("@annotation(com.workit.validator.annotation.ParameterValidator)")
    public void joinPointInAllController() {
    }

    /**
     * 切入点执行前方法
     *
     * @param
     */
    @Before("joinPointInAllController()")
    public void checkParameter(JoinPoint joinPoint) throws IllegalAccessException {
        Object[] args = joinPoint.getArgs();
        for (Object object : args) {
            // 采取快速失败，一个参数校验失败，整个直接结束
            ReflectUtils.checkField(object, object.getClass());
        }

    }
}
