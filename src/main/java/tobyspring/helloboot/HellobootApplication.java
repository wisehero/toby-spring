package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration // 구성 정보를 가지고 있는 클래스라는 것을 알려준다.
@ComponentScan
public class HellobootApplication {

	public static void main(String[] args) {

		// 스프링 컨테이너 띄우기
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			// 스프링 컨테이너로 통합하기
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				WebServer webServer = serverFactory.getWebServer(servletContext ->
						servletContext.addServlet("dispatcherServlet",
								new DispatcherServlet(this)).addMapping("/*"));
				webServer.start();

			}
		};
		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh();

		// 톰캣을 쉽게 띄우기 위한 코드, 복잡한 과정 및 설정 없이 톰캣 서블릿 띄우기 가능
		// ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		// WebServer webServer = serverFactory.getWebServer(servletContext ->
		// 		servletContext.addServlet("dispatcherServlet",
		// 				new DispatcherServlet(applicationContext)).addMapping("/*"));
		// webServer.start();
	}
}

