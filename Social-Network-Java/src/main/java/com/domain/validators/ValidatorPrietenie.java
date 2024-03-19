package com.domain.validators;

import com.domain.Prietenie;

//validator pt clasa Prietenie
public class ValidatorPrietenie implements Validator<Prietenie>{
    /***
     * Validator pt Prietenie
     * @param entity - entitate Prietenie, de validat
     * @throws ValidationException daca nu e valida
     */
    @Override
    public void validate(Prietenie entity) throws ValidationException {
        //in cazul in care id-ul primului prieten nu se regaseste printre utilizatorii
        //din baza de date
        if(entity.getIdPrieten1()==null)
            throw new ValidationException("\nId prieten 1 Invalid!\n");
        //in cazul in care id-ul celui de-al doilea prieten nu
        //se regaseste printre utilizatorii
        //din baza de date
        if(entity.getIdPrieten2()==null)
            throw new ValidationException("\nId prieten 2 Invalid!\n");
        if(entity.getId()==null)
            throw new ValidationException("\nId Invalid!\n");
    }
}
