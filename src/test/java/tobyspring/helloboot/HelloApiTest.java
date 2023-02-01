package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {

	@Test
	void helloApi() {
		// http localhost:8080/hello?name=Spring
		TestRestTemplate rest = new TestRestTemplate();

		ResponseEntity<String> response = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class,
				"SpringBoot");

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
		assertThat(response.getBody()).isEqualTo("*Hello SpringBoot*");
	}
}
