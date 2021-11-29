package cundi.edu.co.demo.exception;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandlerPer extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ModelNotFoundException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorModelNotFoundException(ModelNotFoundException e,
			WebRequest request) {
		e.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ArithmeticException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorModelArithmeticException(ArithmeticException e,
			WebRequest request) {
		System.out.println("Entro 1");
		e.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Ha ocurrido un error", request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorModelConstraintViolationException(ConstraintViolationException e,
			WebRequest request) {
		System.out.println("Entro 11");
		e.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.toString(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConflictException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorConflictException(ConflictException e,
			WebRequest request){
		e.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.toString(), 
					e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ArgumentRequiredException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorArgumentRequiredException(ArgumentRequiredException e,
			WebRequest request){
		e.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), 
					e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(InterruptedException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorArgumentRequiredException(InterruptedException e,
			WebRequest request){
		e.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
					e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(ExecutionException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorArgumentRequiredException(ExecutionException e,
			WebRequest request){
		e.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
					e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}	

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionWrapper> manejadorModelException(Exception e, WebRequest request) {
		System.out.println("Entro 2 ");
		e.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Ha ocurrido un error", request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("Entro 3");
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("Entro 4");
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("Entro 5");
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.METHOD_NOT_ALLOWED.value(),
				HttpStatus.METHOD_NOT_ALLOWED.toString(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("Entro 6");
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}

	// Argumentos no validos -- Investigacion realizada
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("Entro 7");
		System.out.println(ex.getFieldErrors());
		String trasaMostrar = argumentosErroneos(ex);
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				trasaMostrar, request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}
	
	
	private String argumentosErroneos(MethodArgumentNotValidException e) {
		final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		StringBuilder errors = new StringBuilder();

	    for (FieldError fieldError : fieldErrors) {
	    	errors
	          .append(fieldError.getField())
	          .append(": ")
	          .append(fieldError.getDefaultMessage())
	          .append(", ");
//	      errors
//	          .append(fieldError.getField())
//	          .append(": ")
//	          .append(fieldError.getDefaultMessage())
//	          .append(" \n ");
	    }
	    System.out.println(errors.toString());
		return errors.toString();
	}

	// Por averfiguar
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		System.out.println("Entro 8");
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		System.out.println("Entro 9");
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.NOT_FOUND);
	}
	
}
