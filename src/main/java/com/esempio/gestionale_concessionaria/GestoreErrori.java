package com.esempio.gestionale_concessionaria;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GestoreErrori {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail gestisciValidazione(MethodArgumentNotValidException ex) {
        ProblemDetail problema = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problema.setTitle("Dati non validi");
        List<String> errori = ex.getBindingResult().getFieldErrors().stream()
                .map(errore -> errore.getField() + ": " + errore.getDefaultMessage())
                .toList();
        problema.setProperty("errori", errori);
        return problema;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ProblemDetail gestisciNonTrovato(NoSuchElementException ex) {
        ProblemDetail problema = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problema.setTitle("Risorsa non trovata");
        problema.setDetail(ex.getMessage());
        return problema;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail gestisciAutenticazioneFallita(AuthenticationException ex) {
        ProblemDetail problema = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problema.setTitle("Credenziali non valide");
        return problema;
    }

    @ExceptionHandler(IllegalStateException.class)
    public ProblemDetail gestisciStatoNonValido(IllegalStateException ex) {
        ProblemDetail problema = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problema.setTitle("Operazione non consentita");
        problema.setDetail(ex.getMessage());
        return problema;
    }
}
