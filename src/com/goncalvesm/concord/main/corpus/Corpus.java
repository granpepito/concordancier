package com.goncalvesm.concord.main.corpus;

import java.util.ArrayList;

public class Corpus {

    private ArrayList<Texte> listeTexte;

    public Corpus(){
        this.listeTexte = new ArrayList<>();

    }

    public ArrayList<Texte> getTextes(){
        return this.listeTexte;
    }

    //Recherche d'un texte par son titre
    public Texte getTexteByTitre(String titre){
        for (Texte texte: listeTexte){
            if (texte.equals(titre)){
                return texte;
            }
        }
        return null;
    }

    public Texte addTexte(String titre, String contenu){
        Texte nouveauTexte = new Texte();
        nouveauTexte.setTitre(titre);
        nouveauTexte.setContenu(contenu);

        this.listeTexte.add(nouveauTexte);
        return nouveauTexte;
    }

    public Boolean removeTexte(String titre){
        Texte aSupprimer = this.getTexteByTitre(titre);
        if (aSupprimer != null){
            this.listeTexte.remove(aSupprimer);
            return true;
        }
        else {
            return false;
        }
    }

    //Obtention des titres des textes
    public ArrayList<String> getTitres(){
        if (listeTexte.isEmpty()){
            return null;
        }
        ArrayList<String> listeTitres = new ArrayList<>();

        for (Texte texte: listeTexte) {
            listeTitres.add(texte.getTitre());
        }
        return listeTitres;
    }

    public ArrayList<String> getContenus(){
        if (listeTexte.isEmpty()){
            return null;
        }
        ArrayList<String> listeContenus = new ArrayList<>();
        for (Texte texte : listeTexte){
            listeContenus.add(texte.getContenu());
        }
        return listeContenus;
    }


}
