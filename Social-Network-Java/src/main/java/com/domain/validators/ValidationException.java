package com.domain.validators;
//ne definim propriile clase de exceptie
//public class ExceptieProprie extends RuntimeException {/
// /Proprietati si constructori
// public ExceptieProprie(String mesaj) {super(mesaj);
// Apeleaza constructorul superclasei } }}
//O exceptie proprie trebuie sa se încadreze în ierarhia exceptiilor Java, cu alte
//cuvinte clasa care o implementeaza trebuie sa fie subclasa a unei clase deja
//existente în aceasta ierarhie, preferabil una apropiata ca semnificatie sau
//superclasa Exception.
public class ValidationException extends RuntimeException {

    public ValidationException() {}
    public ValidationException(String message) {super(message);}
    public ValidationException(String message, Throwable cause) {super(message, cause);}
    public ValidationException(Throwable cause) {super(cause);}
    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
