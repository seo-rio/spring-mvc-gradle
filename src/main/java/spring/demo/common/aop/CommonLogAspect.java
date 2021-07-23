package spring.demo.common.aop;

import java.util.Enumeration;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
@Aspect
public class CommonLogAspect {

    @Pointcut("execution(* spring.demo.app*..*Controller.*(..))")
    public void commonLoggingPackage() {
    }

    @Before("commonLoggingPackage()")
    public void requestLogging(JoinPoint jp) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
            .getRequest(); // request 정보를 가져온다.

        JSONObject paramObject = getParams(request);
        log.info("==> REQUEST [URI:{} ({})] [{}] [Param : {}] ", request.getRequestURI(), request.getMethod(),
            String.format("%-30s",
                jp.getSignature().getDeclaringType().getSimpleName() + "::" + jp.getSignature().getName()),
            paramObject.size() == 0 ? "No parameter" : paramObject);
    }

    @Around("commonLoggingPackage()")
    public Object responseLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        log.info("<== RESPONSE : {}", result);
        return result;
    }

    /**
     * request 에 담긴 정보를 JSONObject 형태로 반환한다.
     */
    private static JSONObject getParams(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            String replaceParam = param.replaceAll("\\.", "-");
            jsonObject.put(replaceParam, request.getParameter(param));
        }
        return jsonObject;
    }

}
