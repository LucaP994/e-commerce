package it.trinakria.ecommerce.controller.utils;

import it.trinakria.ecommerce.model.dto.ErrorDto;
import it.trinakria.ecommerce.utily.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({Exception.class})
	public final ResponseEntity<Object> genericException(Exception ex) {
		logger.error(String.format("Exception %s has been raised", ex), ex);
		return this.handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ApiException.class})
	public final ResponseEntity<Object> httpClientErrorExceptionException(ApiException ex) {
		logger.error(String.format("API Exception %s has been raised", ex));
		return this.handleExceptionInternal(ex, ex.getStatus());
	}

	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error(String.format("Framework Exception %s has been raised", ex));
		return this.handleExceptionInternal(ex, status);
	}

	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpStatus status) {

		String errorMsg = NestedExceptionUtils.getMostSpecificCause(ex).toString();

		ErrorDto error = new ErrorDto();
		error.setServerMessage(errorMsg);
		error.setHttpStatus(status);
		if (ex instanceof ApiException) {
			error.setUserNotification(((ApiException) ex).getUserNotification());
		} else {
			error.setUserNotification(errorMsg);
		}

		return new ResponseEntity(error, status);
	}


}
