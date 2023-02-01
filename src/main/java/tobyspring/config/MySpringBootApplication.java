package tobyspring.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Retention(RetentionPolicy.RUNTIME) // 왜 RUNTIME으로 줄까? -> 런타임까지 애노테이션이 유지되도록 하기 위함이다.
@Target(ElementType.TYPE) // TYPE = [ "class", "method", "enum" ]
@Configuration
@ComponentScan
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {
}
