package com.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
//se defineste clasa Prietenie
public class Prietenie extends com.domain.Entitate<String> {
    //atributele clasei Prietenie
    private final String idPrieten1,idPrieten2; //id-urile prietenilor
    //intre care vrem sa legam prietenie

    //data prieteniei
    private LocalDateTime data;

    //acest pending este true cand cream o prietenie,adica daca avem doi prieteni
    //x si y, in momentul in care x ii trimite o cerere de prietenie la y
    //pending se va face true
    //in momentul in care y accepta cererea lui x, pending va deveni false
    private Boolean pending = true;

    /***
     * constructor
     * @param id - id prietenie
     * @param idPrieten1 - id prieten 1
     * @param idPrieten2 -  id prieten 2
     */

    //constructorul cu parametrii
    public Prietenie(String id,String idPrieten1, String idPrieten2, LocalDateTime data,Boolean pending){
        super(id); //il mostentim din clasa Entitate
        this.idPrieten1=idPrieten1; // pt id-ul primului prieten
        this.idPrieten2=idPrieten2; // pt id-ul celui de-al doilea prieten
        this.data=data; // pt data
        this.pending=pending; // pt pending
    }

    //constructor in cazul in care nu dau data si pending
    public Prietenie(String id,String idPrieten1,String idPrieten2){
        super(id);
        this.idPrieten1=idPrieten1;
        this.idPrieten2=idPrieten2;
        this.data=LocalDateTime.now();
        this.pending=pending;
    }
    public Prietenie(String id,String idPrieten1, String idPrieten2, LocalDateTime data){
        super(id);
        this.idPrieten1=idPrieten1;
        this.idPrieten2=idPrieten2;
        this.data=data;
        this.pending=pending;
    }
    public Prietenie(String id,String idPrieten1, String idPrieten2,Boolean pending){
        super(id);
        this.idPrieten1=idPrieten1;
        this.idPrieten2=idPrieten2;
        this.data=LocalDateTime.now();
        this.pending=pending;
    }

    //reprezentarea string  a unei prietenii
    //
    @Override
    public String toString() {
        // we use DateTimeFormatter to uniformly format dates and times in an app
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dataFormatata=data.format(formatter);
        return "Prietenie{" +
                "idPrieten1='" + idPrieten1 + '\'' +
                ", idPrieten2='" + idPrieten2 + '\'' +
                ", data=" + dataFormatata +
                ", id=" + id +
                '}';
    }

    //metoda equals
    //returnează egalitatea referințelor;
    //trebuie să fie suprascrisă pentru a realiza egalitatea de conţinut
    // trebuie sa comparam pe rand id-ul primului prieten,id-ul celui de-al doilea prieten,data, ca sa vedem daca sunt egale
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Prietenie prietenie = (Prietenie) o;
        return Objects.equals(idPrieten1, prietenie.idPrieten1) && Objects.equals(idPrieten2, prietenie.idPrieten2) && Objects.equals(data, prietenie.data);
    }

    //suprascriem metoda hashCode
    //returnează valoarea codului de dispersie pentru
    //obiect; valorile sunt diferite pentru obiecte diferite
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idPrieten1, idPrieten2, data);
    }

    /***
     * Getter id prieten 1 (settere nu am ca s finale)
     * @return id prieten 1
     */
    public String getIdPrieten1() {
        return idPrieten1;
    }


    /***
     * Getter id prieten 2 (settere nu am ca s finale)
     * @return id prieten 2
     */
    public String getIdPrieten2() {
        return idPrieten2;
    }


    //getter pt data
    public LocalDateTime getData() {return data;}


    //setter pt data

    public void setData(LocalDateTime data) {
        this.data = data;
    }


    //getter pentru pending
    public Boolean getPending() {
        return pending;
    }


    //setter pentru pending
    public void setPending(Boolean pending) {
        this.pending = pending;
    }
}
