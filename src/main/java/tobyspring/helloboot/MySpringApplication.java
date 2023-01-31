package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
	public static void run(Class<?> applicationClass, String... args) {

		// 스프링 컨테이너 생성
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			// 스프링 컨테이너로 통합하기
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
				// dispatcherServlet.setApplicationContext(this); 이게 없어도 스프링 컨테이너가 등록됨
				WebServer webServer = serverFactory.getWebServer(servletContext ->
						servletContext.addServlet("dispatcherServlet", dispatcherServlet)
								.addMapping("/*"));
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
