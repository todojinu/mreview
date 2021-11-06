package org.zerock.mreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@PropertySource(value = "file:c:/dev/mreview/config/config.properties")  //별도로 분리된 DB설정 파일 로드를 위해 추가
@SpringBootApplication
@EnableJpaAuditing  //JPA Auditing을 사용하기위해 @EnableJpaAuditing 을 적용한다.
public class MreviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MreviewApplication.class, args);
	}

}
