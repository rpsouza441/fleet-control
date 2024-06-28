package br.dev.rodrigopinheiro.fleetControl.controller;

import br.dev.rodrigopinheiro.fleetControl.exception.FleetControlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(FleetControlException.class)
    public ProblemDetail handlePicPayException(FleetControlException e){
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        var fieldErrors=e.getFieldErrors()
                .stream()
                .map(f-> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Your Request parameters didn't validate.");
        pb.setProperty("Invalid-Params", fieldErrors);

        return pb;
    }

    private record InvalidParam(String name, String reason){}
}
