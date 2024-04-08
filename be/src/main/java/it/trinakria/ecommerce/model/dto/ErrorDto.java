package it.trinakria.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorDto {

	private String serverMessage;
	private String userNotification;
	private HttpStatus httpStatus;
}
