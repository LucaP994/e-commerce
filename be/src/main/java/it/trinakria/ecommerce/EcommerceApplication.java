package it.trinakria.ecommerce;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication(scanBasePackages = "it.trinakria.ecommerce.config")
@Slf4j
public class EcommerceApplication {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) throws Exception {
		SpringApplication.run(EcommerceApplication.class, args);
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date data = new Date();
		String newDate = dt.format(data);
		log.info("========= Application started at {} =========",newDate);
	}

}
