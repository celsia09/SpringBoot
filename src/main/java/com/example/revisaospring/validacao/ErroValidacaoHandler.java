package com.example.revisaospring.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormulario> handle(MethodArgumentNotValidException exception){
       List<ErroFormulario> erros = new ArrayList<>();
       List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
       fieldErrors.forEach(e -> {
           //String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
           ErroFormulario erro = new ErroFormulario(e.getField(), e.getDefaultMessage());
           erros.add(erro);
       });

       return erros;
    }
}
