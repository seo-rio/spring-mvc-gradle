package spring.demo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.StreamSupport;

@Slf4j
@Component
public class PropertyLogger {
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        final Environment env = event.getApplicationContext().getEnvironment();

        log.debug("\t\t\t === Environment and configuration === \t\t\t");
        log.debug("Active profiles : {}", Arrays.toString(env.getActiveProfiles()));
        final MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
        StreamSupport.stream(sources.spliterator(), false).filter(ps -> ps instanceof EnumerablePropertySource).map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::stream).distinct().filter(prop -> (!(prop.contains("credentials") || prop.contains("password") || prop.contains("class") || prop.contains("library") || prop.contains("Path") || prop.contains("line.separator"))) && env.getProperty(prop).length() > 1)
                .forEach(prop -> log.debug("{} : {}", String.format("%-50s", prop), String.format("%-100s", env.getProperty(prop))));
    }

}
