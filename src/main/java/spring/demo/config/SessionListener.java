package spring.demo.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

/**
 * Session 설정
 */
@Slf4j
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(415 * 415); // 약 48시간 세션
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.debug("=== Destroy Session ===");
    }
}
