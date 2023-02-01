package tobyspring.study;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {

	@Test
	void configuration() {
		// Common common = new Common();
		// Assertions.assertThat(common).isSameAs(common);

		// MyConfig myConfig = new MyConfig();
		// Bean1 bean1 = myConfig.bean1();
		// Bean2 bean2 = myConfig.bean2();
		//
		// Assertions.assertThat(bean1.common).isSameAs(bean2.common);

	}

	@Configuration
	static class MyConfig {
		@Bean
		Common common() {
			return new Common();
		}

		@Bean
		Bean1 bean1() {
			return new Bean1(common());
		}

		@Bean
		Bean2 bean2() {
			return new Bean2(common());
		}
	}

	static class Bean1 {
		private final Common common;

		Bean1(Common common) {
			this.common = common;
		}
	}

	static class Bean2 {
		private final Common common;

		Bean2(Common common) {
			this.common = common;
		}
	}

	static class Common {

	}
}
