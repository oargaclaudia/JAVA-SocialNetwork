package com.domain;

import java.util.Objects;
//noi consideram id-ul de tip String
public class Utilizator extends com.domain.Entitate<String> {
    //atributele clasei Utilizator
    private String email; //emailul cu care utilizatorul se va conecta
    private String parola;  //parola utilizatorului
    private String nume; //numele utilizatorului
    private String prenume; //prenumele utilizatorului

    //constructorul cu parametrii
    public Utilizator(String id, String email,String parola, String nume, String prenume){
        super(id); //mostenim din clasa de baza Entitate; noi avem deja this.id=id ,setter in
        //clasa Entitate,deci nu il mai scriem si aici si il mostenim cu super
        this.email=email; // pt email
        this.parola=parola; // pt parola
        this.nume=nume; // pt nume
        this.prenume=prenume; // pt prenume
    }

    //metode suprascrise

    //reprezentarea string a unui utilizator
    @Override
    public String toString() {return id+" "+email+" "+parola+" "+nume+" "+prenume;}

    //suprascriem metoda hashCode
    //returnează valoarea codului de dispersie pentru
    //obiect; valorile sunt diferite pentru obiecte diferite
    @Override
    public int hashCode(){return Objects.hash(email,parola,nume,prenume);}


    //suprascriem metoda equals
    //returnează egalitatea referințelor;
    //trebuie să fie suprascrisă pentru a realiza egalitatea de conţinut
    // trebuie sa comparam pe rand emailul,parola si numele ca sa vedem daca sunt egale
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Utilizator that = (Utilizator) o;
        return Objects.equals(email, that.email) && Objects.equals(parola, that.parola) && Objects.equals(nume, that.nume) && Objects.equals(prenume, that.prenume);
    }

    //gettere si settere

    //setter pt email
    public void setEmail(String id) {
        this.email = id;
    }

    //setter pt nume
    public void setNume(String nume) {
        this.nume = nume;
    }

    //setter pt prenume
    public void setPrenume(String prenume){
        this.prenume=prenume;
    }

    //getter pt email
    public String getEmail() {
        return email;
    }

    //getter pt nume
    public String getNume() {
        return nume;
    }

    //getter pt prenume
    public String getPrenume(){
        return prenume;
    }

    //getter pr parola
    public String getParola() {
        return parola;
    }

    //setter pt parola
    public void setParola(String parola) {
        this.parola = parola;
    }
}
