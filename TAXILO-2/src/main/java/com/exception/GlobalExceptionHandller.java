package com.exception;

import java.time.LocalDateTime;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonParseException;

@ControllerAdvice
public class GlobalExceptionHandller {
	
	
	@ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorDetails> CustomerException(CustomerException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(DriverException.class)
    public ResponseEntity<ErrorDetails> DriverException(DriverException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }
	
	
	@ExceptionHandler(TripException.class)
    public ResponseEntity<ErrorDetails> TripException(TripException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }
	

	 @ExceptionHandler(CabException.class)
	    public ResponseEntity<ErrorDetails> CabException(CabException exception, WebRequest webRequest){

	        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

	        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	    }


	    /*--------------------------------------------  Exception  --------------------------------------------------*/
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorDetails> Exception(Exception exception, WebRequest webRequest){

	        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

	        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	    }


	    /*--------------------------------------------  Illegal Argument Exception  --------------------------------------------------*/
	    @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<ErrorDetails> IllegalArgumentException(IllegalArgumentException exception, WebRequest webRequest){

	        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

	        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	    }


	    /*--------------------------------------------  Null Pointer Exception  --------------------------------------------------*/
	    @ExceptionHandler(NullPointerException.class)
	    public ResponseEntity<ErrorDetails> NullPointerException(NullPointerException exception, WebRequest webRequest){

	        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

	        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	    }


	    /*--------------------------------------------  Json Parse Exception  --------------------------------------------------*/
	    @ExceptionHandler(JsonParseException.class)
	    public ResponseEntity<ErrorDetails> BeanCreationException(JsonParseException exception, WebRequest webRequest){

	        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

	        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	    }


	    /*--------------------------------------------  Validation Exception  --------------------------------------------------*/
	    @ExceptionHandler(ValidationException.class)
	    public ResponseEntity<ErrorDetails> BeanCreationException(ValidationException exception, WebRequest webRequest){

	        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

	        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	    }
	
}
