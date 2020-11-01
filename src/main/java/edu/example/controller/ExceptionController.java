package edu.example.controller;

import edu.example.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityIllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseEntity handleEntityIllegalArgumentException(EntityIllegalArgumentException e) {
        return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseEntity handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        return createErrorResponseEntity(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityHasDetailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseEntity handleEntityHasDetailException(EntityHasDetailException e) {
        return createErrorResponseEntity(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseEntity handleEntityNotFoundException(EntityNotFoundException e) {
        return createErrorResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    private static ErrorResponseEntity createErrorResponseEntity(BaseException e, HttpStatus httpStatus) {
        return new ErrorResponseEntity(e.getMessage(), httpStatus.getReasonPhrase(), httpStatus.value());
    }
}
