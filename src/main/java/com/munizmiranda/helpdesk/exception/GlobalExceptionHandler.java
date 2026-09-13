package com.munizmiranda.helpdesk.exception;


import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTicketNotFound(TicketNotFoundException ex) {
        Map<String, Object> corpo = new HashMap<>();
        corpo.put("timestamp", LocalDateTime.now());
        corpo.put("status", HttpStatus.NOT_FOUND.value());
        corpo.put("erro", "Ticket não encontrado");
        corpo.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpo);
    }

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
    Map<String, Object> corpo = new HashMap<>();
    corpo.put("timestamp", LocalDateTime.now());
    corpo.put("status", HttpStatus.BAD_REQUEST.value());
    corpo.put("erro", "Dados inválidos");

    Map<String, String> erros = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(erro ->
            erros.put(erro.getField(), erro.getDefaultMessage())
    );
    corpo.put("mensagens", erros);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpo);
}
@ExceptionHandler(EmailJaCadastradoException.class)
public ResponseEntity<Map<String, Object>> handleEmailJaCadastrado(EmailJaCadastradoException ex) {
    Map<String, Object> corpo = new HashMap<>();
    corpo.put("timestamp", LocalDateTime.now());
    corpo.put("status", HttpStatus.CONFLICT.value());
    corpo.put("erro", "Email já cadastrado");
    corpo.put("mensagem", ex.getMessage());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(corpo);
}

@ExceptionHandler(CredenciaisInvalidasException.class)
public ResponseEntity<Map<String, Object>> handleCredenciaisInvalidas(CredenciaisInvalidasException ex) {
    Map<String, Object> corpo = new HashMap<>();
    corpo.put("timestamp", LocalDateTime.now());
    corpo.put("status", HttpStatus.UNAUTHORIZED.value());
    corpo.put("erro", "Falha na autenticação");
    corpo.put("mensagem", ex.getMessage());

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(corpo);
}
@ExceptionHandler(AcessoNegadoException.class)
public ResponseEntity<Map<String, Object>> handleAcessoNegado(AcessoNegadoException ex) {
    Map<String, Object> corpo = new HashMap<>();
    corpo.put("timestamp", LocalDateTime.now());
    corpo.put("status", HttpStatus.FORBIDDEN.value());
    corpo.put("erro", "Acesso negado");
    corpo.put("mensagem", ex.getMessage());

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(corpo);
}

}