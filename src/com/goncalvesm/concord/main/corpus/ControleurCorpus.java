package com.goncalvesm.concord.main.corpus;

import com.goncalvesm.concord.main.index.CoordinateurIndex;

import java.util.ArrayList;

public class ControleurCorpus {

    private Corpus corpus;
    private CoordinateurIndex coordinateurIndex;

    public ControleurCorpus(Corpus corpus, CoordinateurIndex coordinateurIndex){
        this.corpus = corpus;
        this.coordinateurIndex = coordinateurIndex;
    }

    //Ajout d'un texte au corpus
    private void addTexte(String titre, String contenu){
        this.corpus.addTexte(titre, contenu);

    }

    //Obtention des textes
    private ArrayList<Texte> getCorpus() {
        return corpus.getTextes();
    }

    //Ajout de l'ensemble des textes
    public void setCorpus(ArrayList<String> titres, ArrayList<String> contenus){
        //System.out.println(titres.size() + " et " + contenus.size());
        for (int i = 0; i < titres.size(); i++){
            this.addTexte(titres.get(i), contenus.get(i));
        }
        //Lancement de l'indexation
        coordinateurIndex.setCorpus(this.getCorpus());
    }

    //Obtenir le coodinateur d'index
    public CoordinateurIndex getCoordinateurIndex() {
        return coordinateurIndex;
    }

    //Obtenir le titre de chaque texte
    public ArrayList<String> getTitres(){
        return this.corpus.getTitres();
    }

    //Obtenir le contenu de chaque texte
    public ArrayList<String> getContenus(){
        return this.corpus.getContenus();
    }

}
