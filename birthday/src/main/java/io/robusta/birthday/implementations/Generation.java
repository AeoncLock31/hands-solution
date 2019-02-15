package io.robusta.birthday.implementations;

import io.robusta.birthday.interfaces.IGeneration;
import io.robusta.birthday.interfaces.IPeopleCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas Zozol on 04/10/2016.
 */
public class Generation implements IGeneration{

    List<PeopleCollection> collections;

    public Generation(){

    }

    public Generation(int n, int collectionSize) {
        this.collections = createAllRandom(n, collectionSize);
    }

    @Override
    public PeopleCollection createRandom(int size) {
        return new PeopleCollection(size);
    }

    @Override
    public List<PeopleCollection> createAllRandom(int n, int size) {
        ArrayList<PeopleCollection> liste = new ArrayList<PeopleCollection>();
        for (int i =0; i<n; i++) {
        	PeopleCollection p = new PeopleCollection(size);
        	liste.add(p);
        }
        return liste;
    }

    @Override
    public List<PeopleCollection> getPeopleCollections() {
        return this.collections;
    }

    @Override
    public int getNumberOfCollectionsThatHasTwoPeopleWithSameBirthday() {
        return 0;
    }

    @Override
    public boolean isLessThan50() {
        return false;
    }


}
