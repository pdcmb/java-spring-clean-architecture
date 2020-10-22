package com.pdcmb.covid19stats.presentation.handlers;

import com.pdcmb.covid19stats.domain.exceptions.FilterMalformedException;
import com.pdcmb.covid19stats.presentation.exceptions.ResourceNotFoundException;
import com.pdcmb.covid19stats.presentation.models.ApiErrorResponseModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public final ResponseEntity<ApiErrorResponseModel> handleResourceNotFoundException(
        ResourceNotFoundException ex){
            ApiErrorResponseModel error = 
                    new ApiErrorResponseModel()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reason("Resource not found")
                            .message(ex.getMessage());       
            return new ResponseEntity<ApiErrorResponseModel>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({FilterMalformedException.class})
    public final ResponseEntity<ApiErrorResponseModel> handleFilterMalformedException(
        FilterMalformedException ex){
            ApiErrorResponseModel error = 
                    new ApiErrorResponseModel()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .reason("Bad Request")
                            .message(ex.getMessage());       
            return new ResponseEntity<ApiErrorResponseModel>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ApiErrorResponseModel> handleServerException(Exception ex){
            ApiErrorResponseModel error = 
                    new ApiErrorResponseModel()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .reason("Server error")
                            .message(ex.getMessage());       
            return new ResponseEntity<ApiErrorResponseModel>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
