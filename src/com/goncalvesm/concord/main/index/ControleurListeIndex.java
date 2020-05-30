package com.goncalvesm.concord.main.index;

import com.goncalvesm.concord.main.contexte.ListeContexte;

import java.util.ArrayList;
import java.util.Hashtable;

public class ControleurListeIndex {


    private Hashtable<String, ListeIndex> listesIndex;

    public ControleurListeIndex(){
        this.listesIndex = new Hashtable<>();
    }

    /*
     * Créer une nouvelle liste d'index pour un texte avec son @titre
    public ListeIndex newListeIndex(String titre){
        ListeIndex nouvelleListe = new ListeIndex();

        this.listesIndex.put(titre, nouvelleListe);
        return nouvelleListe;
    }*/

    //Ajouter/mettre à jour un index
    public synchronized void addIndex(String titre, String mot, Integer position){
        ListeIndex listeAModifier = getListeIndexByTitre(titre);
        if (listeAModifier != null){
            listeAModifier.add(mot, position);
        }
        else {
            ListeIndex nouvelleListe = new ListeIndex();
            nouvelleListe.add(mot, position);
            this.listesIndex.put(titre, nouvelleListe);
        }
    }

    /*//Obtenir un index pour un texte
    public Index getIndex(String titre, String mot){
        ListeIndex listeAModifier = getListeIndexByTitre(titre);
        if (listeAModifier != null){
            return listeAModifier.getIndex(mot);
        }
        return null;
    }*/

    //Obtenir les positions d'un mot pour chaque texte
    public synchronized Hashtable<String, Index> getAllIndexesOf(String mot){
        Hashtable<String, Index> indexParTitre = new Hashtable<>();
        for (String titreTexte: listesIndex.keySet()){

            if (listesIndex.get(titreTexte).contains(mot)){
                ListeIndex listeIndex = getListeIndexByTitre(titreTexte);
                if (listeIndex != null){
                    indexParTitre.put(titreTexte, listeIndex.getIndex(mot));
                }
            }

        }
        return indexParTitre;
    }

    //Obtenir tous les indexes d'un texte
    public ListeIndex getListeIndexByTitre(String titre){
        if (containsTexte(titre)){
            return listesIndex.get(titre);
        }
        return null;
    }

    //Verifie si le controleur possède les liste d'index pour un texte.
    public Boolean containsTexte(String titre){
        return this.listesIndex.containsKey(titre);
    }

    //Verifie si un mot se trouve dans une des listes d'index
    public Boolean containsIndexOf(String mot){
        for (String titre: listesIndex.keySet()){
            ListeIndex listeIndex = listesIndex.get(titre);
            if (listeIndex.contains(mot)){
                return true;
            }
        }
        return false;
    }


    /*public Boolean isEmpty(){
        return this.listesIndex.isEmpty();
    }*/
}
