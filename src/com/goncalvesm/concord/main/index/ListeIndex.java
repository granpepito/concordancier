package com.goncalvesm.concord.main.index;

import java.util.ArrayList;
import java.util.Hashtable;

public class ListeIndex {

    private Hashtable<String, Index> indexes;


    public ListeIndex(){
        this.indexes = new Hashtable<>();
    }

    private Index newIndex(String mot){
        Index nouvelIndex = new Index(mot);
        this.indexes.put(mot, nouvelIndex);
        return nouvelIndex;
    }

    //Ajouter un index
    public synchronized void add(String mot, Integer position){
        Index aModifier = getIndex(mot);
        if (aModifier != null){
            aModifier.add(position);
        }
        else {
            Index nouvelIndex = newIndex(mot);
            nouvelIndex.add(position);
        }
    }

    //Contiens un mot dans liste d'index
    public Boolean contains(String mot){
        return this.indexes.containsKey(mot);
    }

    //Obtenir un index
    public Index getIndex(String mot){
        if (contains(mot)){
            return this.indexes.get(mot);
        }
        return null;
    }

    /*//Obtenir la lis
    public Hashtable<String, Index> getAllIndex(){
        return this.indexes;
    }*/
}
