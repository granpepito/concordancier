package com.goncalvesm.concord.main.contexte;

import java.util.ArrayList;
import java.util.Hashtable;

public class ListeContexte {

    private Hashtable<String, ArrayList<Contexte>> contextes;

    public ListeContexte(){
        this.contextes = new Hashtable<>();
    }

    public void addContexte(String mot, String pre, String post){
        //Modification du tableau si déjà existant (ajout d'un contexte)
        ArrayList<Contexte> listeContexte = getContextesOf(mot);
        if (listeContexte != null){
            listeContexte.add(new Contexte(pre, post));
        }
        //Creation et ajout d'un contexte
        else {
            listeContexte = new ArrayList<>();
            listeContexte.add(newContexte(pre, post));
            this.contextes.put(mot, listeContexte);
        }
    }

    public void addContextes(String motPrincipal, ArrayList<Contexte> nouveauxContxts){
        ArrayList<Contexte> listeContexte = getContextesOf(motPrincipal);
        if (listeContexte != null){
            this.contextes.remove(motPrincipal);

        }
        this.contextes.put(motPrincipal, nouveauxContxts);
    }

    public void addContexte(String mot, Contexte contexte){
        ArrayList<Contexte> listeContexte = getContextesOf(mot);
        if (listeContexte != null){
            listeContexte.add(contexte);
        }
        //Creation et ajout d'un contexte
        else {
            listeContexte = new ArrayList<>();
            listeContexte.add(contexte);
            this.contextes.put(mot, listeContexte);
        }
    }

    public ArrayList<Contexte> getContextesOf(String mot){
        if (containsContexteOf(mot)){
            return this.contextes.get(mot);
        }
        return null;
    }

    public ArrayList<String> getContexteOfToString(String mot){

        if (containsContexteOf(mot)){
            ArrayList<String> contextesResult = new ArrayList<>();
            ArrayList<Contexte> contextesOf = getContextesOf(mot);
            for (Contexte contexte: contextesOf){
                contextesResult.add(contexte.toString(mot));
            }
            return contextesResult;
        }
        return null;
    }

/*
    private Contexte newContexte(){
        return new Contexte();
    }
*/

    private Contexte newContexte(String pre, String post){
        return new Contexte(pre, post);
    }

    public Boolean containsContexteOf(String mot){
        return this.contextes.containsKey(mot);
    }

}
