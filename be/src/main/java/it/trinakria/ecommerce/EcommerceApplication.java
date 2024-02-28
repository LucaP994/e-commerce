package it.trinakria.ecommerce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
@Slf4j
public class EcommerceApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(EcommerceApplication.class, args);
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date data = new Date();
		String newDate = dt.format(data);
		log.info("========= Application started at {} =========",newDate);
	}

}
