package com.effecti.etapaencontrar.service.exception;

public class PaginaNaoEncontradaException extends RuntimeException {
    public PaginaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
