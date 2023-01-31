package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

	public static void main(String[] args) {

		// 스프링 컨테이너 띄우기
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
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
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh();

		// // 톰캣을 쉽게 띄우기 위한 코드, 복잡한 과정 및 설정 없이 톰캣 서블릿 띄우기 가능
		// ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		// WebServer webServer = serverFactory.getWebServer(servletContext ->
		// 		servletContext.addServlet("dispatcherServlet",
		// 				new DispatcherServlet(applicationContext)).addMapping("/*"));
		// webServer.start();
	}
}

