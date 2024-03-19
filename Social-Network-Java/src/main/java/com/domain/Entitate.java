package com.domain;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class Entitate<ID> {
    //final sa nu poata fi modificat in clasele derivate
    protected final ID id;
//a more convenient way to create a UUID without giving any parameter as input.
    /***
     * constructor
     */
    public Entitate(ID id){
        if(id==null){
            this.id =  (ID)UUID.randomUUID().toString();
        }
        else{
            this.id=id;
        }
    }

    //getter id
    public ID getId() {
        return id;
    }
    //public void setId(ID id) {this.id=id;}

    /***
     * metoda suprascrisa equals
     * @param o - cel cu care comparam
     * @return true daca sunt egale, false altfe;
     */
    //suprascriem metoda equals
    //returnează egalitatea referințelor;
    //trebuie să fie suprascrisă pentru a realiza egalitatea de conţinut în
    //subclase
    @Override
    public boolean equals(Object o) {
        //getClass() returns the runtime class of the object "this".
        // This returned class object is locked by static synchronized method of the represented class.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entitate entitate = (Entitate) o;
        return Objects.equals(id, entitate.id);
    }

    /***
     * Metoda suprascrisa hashCode
     * @return hashCode
     */
    //suprascriem metoda hashCode
    //returnează valoarea codului de dispersie pentru
    //obiect; valorile sunt diferite pentru obiecte diferite
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /***
     * Metoda suprascrisa toString
     * @return reprezentarea string a entitatii
     */
    @Override
    public String toString() {
        return "Entitate{" + "id='" + id + '\'' + '}';
    }
}
