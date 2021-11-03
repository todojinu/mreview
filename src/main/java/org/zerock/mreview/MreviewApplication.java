package org.zerock.mreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "file:c:/dev/mreview/config/config.properties")  //별도로 분리된 DB설정 파일 로드를 위해 추가
@SpringBootApplication
public class MreviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MreviewApplication.class, args);
	}

}
