package com.repo;

import java.util.List;
//interfata repository
//am pus aici antetul functiilor pe care
//urmeaza sa le implementez in PrietenieDBRepository si UtilizatorDBRepository
public interface Repository<ID,entityType> {
    //adauga entitatea
    void adauga(entityType entity);

    //sterge entitatea
    void sterge(entityType entity);

    //cauta entitatea dupa un id dat
    entityType cautaId(String id);

    //returneaza o lista care reprezinta toate entitatile
    List<entityType> getAll();

    //actualizeaza vechea enitate cu noua entitate
    void update(entityType entitate,entityType nouaEntitate);
}
