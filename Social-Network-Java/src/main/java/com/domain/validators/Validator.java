package com.domain.validators;
//interfata Validator
public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
