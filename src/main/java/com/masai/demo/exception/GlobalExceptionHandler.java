package com.masai.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> myExpHandler(IllegalArgumentException ie){

        return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception e, WebRequest req){

        MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(),e.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<MyErrorDetails> categoryExceptionHandler(CategoryException categoryException, WebRequest request){

        MyErrorDetails errorDetails = new MyErrorDetails();
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setMessage(categoryException.getMessage());
        errorDetails.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler(UserException userException, WebRequest request){

        MyErrorDetails errorDetails = new MyErrorDetails();
        errorDetails.setMessage(userException.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressException.class)
    public ResponseEntity<MyErrorDetails> addressExceptionHandler(AddressException addressException, WebRequest request){

        MyErrorDetails errorDetails = new MyErrorDetails();
        errorDetails.setMessage(addressException.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {


        MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }
}
