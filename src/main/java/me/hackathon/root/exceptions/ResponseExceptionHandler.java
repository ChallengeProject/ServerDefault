package me.hackathon.root.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import me.hackathon.root.model.supoort.ExceptionCode;
import me.hackathon.root.model.supoort.ResultContainer;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<ResultContainer<Object>> handleUserAlreadyExistsException(UserAlreadyExistsException e, WebRequest req) {
        ResultContainer<Object> rc = new ResultContainer<>(null);
        rc.setCode(ExceptionCode.INTERNAL_SERVER_ERROR);
        rc.setMessage(e.getMessage());
        System.out.println("exception 일어남" + rc);
        return new ResponseEntity<>(rc, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
