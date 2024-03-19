package com.service;

import com.domain.Prietenie;
import com.domain.Utilizator;
import com.domain.validators.ValidationException;
import com.domain.validators.ValidatorPrietenie;
import com.domain.validators.ValidatorUtilizator;

import com.utils.events.ChangeEventType;
import com.utils.events.FriendshipEntityChangeEvent;
import com.utils.observer.Observable;
import com.utils.observer.Observer;
import com.repo.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Service implements Observable<FriendshipEntityChangeEvent> {
    private final Repository<String,Utilizator> UtilizatorRepository;
    private final Repository<String,Prietenie> PrietenieRepository;

    private String utilizatorCurentId = null;

    private List<Observer<FriendshipEntityChangeEvent>> observers= new ArrayList<>();

    public Service(Repository<String,Utilizator> UtilizatorRepository, Repository<String,Prietenie> PrietenieRepository) {
        this.UtilizatorRepository = UtilizatorRepository;
        this.PrietenieRepository = PrietenieRepository;
    }

    public boolean conectareUtilizator(String email, String parola) {
        for (Utilizator utilizator : UtilizatorRepository.getAll()) {
            if (utilizator.getEmail().equals(email)) {
                if (utilizator.getParola().equals(parola)) {
                    utilizatorCurentId = utilizator.getId();
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean creareUtilizator(String nume, String prenume, String email, String parola) {
        for (Utilizator utilizator : UtilizatorRepository.getAll()) {
            if (utilizator.getEmail().equals(email)){
                return false;
            }
        }
        try {
            Utilizator u = new Utilizator(null, email,parola,nume,prenume);
            UtilizatorRepository.adauga(u);
            utilizatorCurentId = u.getId();
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public void deconectareUtilizator() {
        utilizatorCurentId = null;
    }

    public void trimiteCerere(String Id){
        //caut sa vad daca a trimis cererea deja
        Utilizator userA = UtilizatorRepository.cautaId(utilizatorCurentId);
        Utilizator userB = UtilizatorRepository.cautaId(Id);

        List<Prietenie> lst=obtinePrietenii();
        for(Prietenie el:lst){
            if(el.getIdPrieten1().equals(userA.getId()) && el.getIdPrieten2().equals(userB.getId())){
                throw new ValidationException("AI TRIMIS DEJA ACEASTA CERERE!");
            }
        }

        Prietenie el=new Prietenie(null, utilizatorCurentId, Id);
        PrietenieRepository.adauga(el);
        notifyObservers(new FriendshipEntityChangeEvent(ChangeEventType.ADD, el));
    }

    public void acceptaCerere(String Id){
        Utilizator user = UtilizatorRepository.cautaId(Id);
        List<Prietenie> lst=obtinePrietenii();
        //for(Prietenie el:lst) System.out.println(el);
        for(Prietenie el: lst){
            if(el.getIdPrieten1().equals(user.getId()) && el.getPending()) {
                //System.out.println("BA L-A GASIT");
                Prietenie noua= new Prietenie(el.getId(),el.getIdPrieten1(),el.getIdPrieten2(),el.getData(),false);
                //el.setPending(false);
                PrietenieRepository.update(el,noua);
                notifyObservers(new FriendshipEntityChangeEvent(ChangeEventType.ADD, noua));
                break;
            }
        }
    }

    public Prietenie stergePrieten(String prieten_1,String prieten_2) {
        List<Prietenie> lst = PrietenieRepository.getAll();

        for(Prietenie el:lst){
            if((el.getIdPrieten1().equals(prieten_1) && el.getIdPrieten2().equals(prieten_2)) || (el.getIdPrieten1().equals(prieten_2) && el.getIdPrieten2().equals(prieten_1))){
                PrietenieRepository.sterge(el);
                notifyObservers(new FriendshipEntityChangeEvent(ChangeEventType.ADD, el));
                return el;
            }
        }
        throw new ValidationException("Nu exista o prietenie intre cei doi!");
    }

    public List<Prietenie> obtinePrietenii() {
        List<Prietenie> prietenii = new ArrayList<>();
        for (Prietenie pr : PrietenieRepository.getAll()) {
            if (pr.getIdPrieten1().equals(utilizatorCurentId) || pr.getIdPrieten2().equals(utilizatorCurentId)) {
                prietenii.add(pr);
            }
        }
        return prietenii;
    }

    public List<Utilizator> obtineUtilizatoriCereri() {
        List<Utilizator> prietenii = new ArrayList<>();
        for (Prietenie pr : PrietenieRepository.getAll()) {
            if (pr.getIdPrieten2().equals(utilizatorCurentId) && pr.getPending()) {
                prietenii.add(UtilizatorRepository.cautaId(pr.getIdPrieten1()));
            }
        }
        return prietenii;
    }

    public List<Utilizator> obtineUtilizatoriPrieteni() {
        List<Utilizator> prietenii = new ArrayList<>();
        for (Prietenie pr : PrietenieRepository.getAll()) {
            if ((pr.getIdPrieten1().equals(utilizatorCurentId) || pr.getIdPrieten2().equals(utilizatorCurentId)) && !pr.getPending()) {
                if(!pr.getIdPrieten1().equals(utilizatorCurentId))  prietenii.add(UtilizatorRepository.cautaId(pr.getIdPrieten1()));
                else prietenii.add(UtilizatorRepository.cautaId(pr.getIdPrieten2()));
            }
        }
        return prietenii;
    }

    public List<Utilizator> obtineUtilizatoriNeprieteni() {
        Boolean ok=true;
        List<Utilizator> neprieteni = new ArrayList<>();
        List<Utilizator> lista=obtineUtilizatoriPrieteni();
        lista.addAll(obtineUtilizatoriCereri());
        for(Utilizator user:UtilizatorRepository.getAll()){
            for (Utilizator u : lista) {
                if (user.getId().equals(u.getId())) ok=false;
            }
            if(ok==true && !user.getId().equals(utilizatorCurentId)) neprieteni.add(user);
            ok=true;
        }
        return neprieteni;
    }

    public String getIdCurent() {
        return utilizatorCurentId;
    }
    public Utilizator getUtilizatorCurent() {
        return UtilizatorRepository.cautaId(utilizatorCurentId);
    }



    public void updateUtilizator(String Id,String Nume,String Prenume,String Email,String Parola){
        Utilizator utilizator = UtilizatorRepository.cautaId(Id);
        if(utilizator==null){
            throw new ValidationException("\nAcest utilizator nu exista!\n");
        }
        Utilizator utilizatorNou=new Utilizator(Id,Email,Parola,Nume,Prenume);
        UtilizatorRepository.update(utilizator,utilizatorNou);
    }


    public Iterable<Utilizator> getAllUsers(){
        return UtilizatorRepository.getAll();
    }

    @Override
    public void addObserver(Observer<FriendshipEntityChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<FriendshipEntityChangeEvent> e) {
        //observers.remove(e);
    }

    @Override
    public void notifyObservers(FriendshipEntityChangeEvent t) {
        observers.stream().forEach(x->x.update(t));
    }
}
