package org.zerock.mreview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootTest
class MreviewApplicationTests {

	@Value("${spring.datasource.driver-class-name}")
	private String driver;

	@Test
	void contextLoads() {
		System.out.println(driver);
	}

}
