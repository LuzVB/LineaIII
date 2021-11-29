package cundi.edu.co.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {
	@ExceptionHandler(CustomException.class)
	public MensajeError customException(CustomException exception){
		return new MensajeError("customException", exception.getMessage());
	}

}
