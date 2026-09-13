package com.munizmiranda.helpdesk.exception;

public class CredenciaisInvalidasException extends RuntimeException {
    public CredenciaisInvalidasException() {
        super("Email ou senha inválidos");
    }
}
