package com.goncalvesm.concord.main.index;

import com.goncalvesm.concord.main.contexte.EcranSaisieContexte;
import com.goncalvesm.concord.main.corpus.EcranSaisieCorpus;
import com.goncalvesm.concord.main.corpus.Texte;
import com.goncalvesm.concord.main.fournisseurs.AlerteThreadTermine;
import com.goncalvesm.concord.main.fournisseurs.FournisseurIndex;

import java.util.ArrayList;


public class CoordinateurIndex implements AlerteThreadTermine {

    private ControleurListeIndex controleurListeIndex;
    private EcranSaisieContexte ecranSaisieContexte;
    private EcranSaisieCorpus ecranSaisieCorpus;
    private ArrayList<Texte> corpus;
    private ArrayList<Thread> fournisseursIndex;

    public CoordinateurIndex(ControleurListeIndex controleurListeIndex){
        this.corpus = new ArrayList<>();
        this.controleurListeIndex = controleurListeIndex;
    }

    private void commencerIndexation(){
        fournisseursIndex = new ArrayList<>();
        //Création de fournisseur pour chaque texte
        for (int i = 0; i < corpus.size(); i++){
            Texte texte = corpus.get(i);
            //Fournisseur d'index pour le texte i.
            FournisseurIndex nouveauFournisseur = new FournisseurIndex(controleurListeIndex, texte.getTitre(), texte.getContenu());
            //abonnement à l'alerte
            nouveauFournisseur.addListener(this);
            fournisseursIndex.add(nouveauFournisseur);
            nouveauFournisseur.start();
        }
    }

    //Ajouter l'écran de recherche de contexte afin de pouvoir le notifier
    public void setEcranSaisieContexte(EcranSaisieContexte ecranSaisieContexte){
        this.ecranSaisieContexte = ecranSaisieContexte;
    }

    public void setEcranSaisieCorpus(EcranSaisieCorpus ecranSaisieCorpus) {
        this.ecranSaisieCorpus = ecranSaisieCorpus;
    }

    /*public ArrayList<Texte> getCorpus() {
        return corpus;
    }*/

    public void setCorpus(ArrayList<Texte> corpus){
        this.corpus = corpus;
        commencerIndexation();
    }

    @Override
    public void alerterTerminaison(Thread thread) {
        //On enlève le fournisseur de la liste de fournisseur
        //On défait la liaison entre le fournisseur et le coordinateur.
        if (fournisseursIndex.contains(thread)){
            FournisseurIndex fournisseurIndex = (FournisseurIndex) thread;
            fournisseurIndex.removeListener(this);
            fournisseursIndex.remove(fournisseurIndex);
        }

        if (fournisseursIndex.isEmpty()){
            //Notification à l'écran de saisie de corpus qu'il peut à nouveau accepter des textes.
            ecranSaisieCorpus.activerConfirmerBtn();
            //Notification à l'écran de recherche de contexte que les recherches sont acceptées.
            ecranSaisieContexte.activerRecherche();
        }
    }
}
