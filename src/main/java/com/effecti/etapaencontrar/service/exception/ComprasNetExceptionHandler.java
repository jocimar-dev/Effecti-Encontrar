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

    @ExceptionHandler(MensagemLidaException.class)
    public ResponseEntity<Object> handleMensagemLidaException(MensagemLidaException ex) {
        String errorMessage = "Não foi possível marcar como lida a mensagem: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
