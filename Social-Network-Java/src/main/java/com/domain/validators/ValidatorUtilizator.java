package com.domain.validators;

import com.domain.Utilizator;
//validam un utilizator sa vedem daca e bun sau daca nu e bun
//pentru ca se pot introduce de la tastatura anumite erori
//atunci voi prinde o exceptie (daca ceea ce am introdus
//nu e valid,se arunca exceptie

public class ValidatorUtilizator implements Validator<Utilizator>{
    /***
     * Validator pt Utilizator
     * @param entity - entitatea Utilizator
     * @throws ValidationException daca nu e valid
     */
    @Override
    public void validate(Utilizator entity) throws ValidationException {
      //trim taie spatii de dinainte si dupa
       // The trim() method in Java String is
        // a built-in function that eliminates leading and trailing spaces.
        //vad daca numele e valid
        String nume = entity.getNume().trim();
        if (nume == null || nume.isEmpty() || nume.charAt(0) < 'A' || nume.charAt(0) > 'Z') {
            throw new ValidationException("\nNume invalid!\n");
        }

        //vad daca prenumele e valid
        String prenume = entity.getPrenume().trim();
        if (prenume == null || prenume.isEmpty() || prenume.charAt(0) < 'A' || prenume.charAt(0) > 'Z') {
            throw new ValidationException("\nPrenume invalid!\n");
        }

        //vad daca emailul e valid
        if(entity.getEmail()==null || entity.getEmail().equals(""))
            throw new ValidationException("\nEmail Invalid!\n");

        //vad daca parola e valida
        if(entity.getParola()==null || entity.getParola().equals(""))
            throw new ValidationException("\nParola Invalida!\n");
    }

}
