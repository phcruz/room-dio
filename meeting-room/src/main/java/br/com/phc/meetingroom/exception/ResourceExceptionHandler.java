package br.com.phc.meetingroom.exception;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.common.net.HttpHeaders;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String X_TRANSACTION_ID = "x-transaction-id";
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<StandardError> badRequestExceptionHandler(BadRequestException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new StandardError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI(), getErrorDetails(e)));
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<StandardError> unauthorizedExceptionHandler(UnauthorizedException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new StandardError(HttpStatus.UNAUTHORIZED, e.getMessage(), request.getRequestURI(), getErrorDetails(e)));
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<StandardError> forbiddenExceptionHandler(ForbiddenException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(new StandardError(HttpStatus.FORBIDDEN, e.getMessage(), request.getRequestURI(), getErrorDetails(e)));
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> notFoundExceptionHandler(NotFoundException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new StandardError(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI(), getErrorDetails(e)));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<StandardError> sqlExceptionHandler(RuntimeException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new StandardError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getRequestURI(), getErrorDetails(e)));
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status)
				.body(new StandardError(status, ex.getMessage(), request.getContextPath(), getErrorDetails(ex)));
	}

	private ErrorDetails getErrorDetails(Exception e) {
		ErrorDetails details = new ErrorDetails();
		details.setUniqueId(UUID.randomUUID().toString());
		details.setInformationCode(null);
		details.setMessage(e.getMessage());
		return details;
	}

}