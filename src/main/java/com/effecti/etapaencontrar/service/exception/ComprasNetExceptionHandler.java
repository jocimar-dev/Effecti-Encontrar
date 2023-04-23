package com.effecti.etapaencontrar.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ComprasNetExceptionHandler {

    @ExceptionHandler(PaginaNaoEncontradaException.class)
    public ResponseEntity<Object> handlePaginaNaoEncontradaException(PaginaNaoEncontradaException ex) {
        String errorMessage = "Página não encontrada: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaginaNaoEncontradaException.class)
    public ResponseEntity<Object> handlePaginaNaoEncontradaException(MensagemLidaException ex) {
        String errorMessage = "ID não encontrado: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
