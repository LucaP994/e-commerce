package it.trinakria.ecommerce.utily;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
	private final HttpStatus status;
	private final String userNotification;

	public ApiException(HttpStatus status, String serverMessage, String userNotification) {
		super(serverMessage);
		this.status = status;
		this.userNotification = userNotification;
	}

	public ApiException(HttpStatus status, String serverMessage, Throwable cause, String userNotification) {
		super(serverMessage, cause);
		this.status = status;
		this.userNotification = userNotification;
	}

	public String getUserNotification() {
		return this.userNotification != null ? this.userNotification : this.getMessage();
	}

}
