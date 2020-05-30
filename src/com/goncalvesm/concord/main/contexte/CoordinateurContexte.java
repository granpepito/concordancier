package com.goncalvesm.concord.main.contexte;

import com.goncalvesm.concord.main.corpus.ControleurCorpus;
import com.goncalvesm.concord.main.fournisseurs.AlerteThreadTermine;
import com.goncalvesm.concord.main.fournisseurs.FournisseurContexte;
import com.goncalvesm.concord.main.index.ControleurListeIndex;
import com.goncalvesm.concord.main.index.Index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class CoordinateurContexte implements AlerteThreadTermine {

    private ControleurListeIndex controleurListeIndex;
    private ControleurCorpus controleurCorpus;
    private EcranAffichageContexte ecranAffichageContexte;
    private ControleurListeContexte controleurListeContexte;
    private ArrayList<FournisseurContexte> fournisseurContextes;

    private int rayon;
    private String motRecherche;


    public CoordinateurContexte(ControleurListeContexte controleurListeContexte){
        this.controleurListeContexte = controleurListeContexte;
        this.fournisseurContextes = new ArrayList<>();
        this.motRecherche = "";
    }

    public void setControleurListeIndex(ControleurListeIndex controleurListeIndex) {
        this.controleurListeIndex = controleurListeIndex;
    }

    public void setControleurCorpus(ControleurCorpus controleurCorpus) {
        this.controleurCorpus = controleurCorpus;
    }

    public void setEcranAffichageContexte(EcranAffichageContexte ecranAffichageContexte) {
        this.ecranAffichageContexte = ecranAffichageContexte;
    }

    public int getRayon() {
        return rayon;
    }

    public String getMotRecherche() {
        return motRecherche;
    }

    public void setMotRecherche(String motRecherche) {
        this.motRecherche = motRecherche;
    }

    public void startRecherche(String mot, int rayon){
        if (controleurListeIndex != null && controleurListeContexte != null && controleurCorpus != null && ecranAffichageContexte != null){
            this.motRecherche = mot;
            this.rayon = rayon;

            ArrayList<String> titres = controleurCorpus.getTitres();
            ArrayList<String> contenus = controleurCorpus.getContenus();


            if ( controleurListeContexte.containsTextes(titres) && controleurListeContexte.containsContexteOf(motRecherche)){
                //Envoie des contextes à l'écran d'affichage
                HashMap<String, ArrayList<String>> allContexteOf =controleurListeContexte.getAllContexteOf(motRecherche, rayon);
                this.ecranAffichageContexte.afficherResultat(motRecherche, allContexteOf);
               /* for (String titre: allContexteOf.keySet()){
                    System.out.println(titre);
                    for (String contexteOf: allContexteOf.get(titre)){
                        System.out.println("\t" + contexteOf);
                    }
                }*/

            }
            else if (controleurListeIndex.containsIndexOf(motRecherche)){
                Hashtable<String, Index> allIndex = controleurListeIndex.getAllIndexesOf(motRecherche.toLowerCase());
                for (int i = 0; i < titres.size(); i++){
                    String titreActuel = titres.get(i);
                    String contenuActuel = contenus.get(i);
                    Index indexActuel = allIndex.get(titreActuel);
                    if (indexActuel != null){
                        //Délégation de la recherche de contexte au founisseur de contexte
                        //System.out.println("Pour "+ titreActuel + " -->  index: " + indexActuel.getPositions().toString());
                        FournisseurContexte nouveauFournisseur = new FournisseurContexte(controleurListeContexte, titreActuel, contenuActuel, motRecherche, indexActuel);
                        //nouveauFournisseur.setRayon(rayon);
                        nouveauFournisseur.addListener(this);
                        fournisseurContextes.add(nouveauFournisseur);

                        nouveauFournisseur.start();
                    }

                }

            }
            else {
                ecranAffichageContexte.afficherErreur(motRecherche);
            }
        }

    }

    @Override
    public void alerterTerminaison(Thread thread) {
        if (fournisseurContextes.contains(thread)){
            FournisseurContexte fournisseurContexte = (FournisseurContexte) thread;
            fournisseurContexte.removeListener(this);
            fournisseurContextes.remove(fournisseurContexte);
        }

        if (fournisseurContextes.isEmpty()){
            //Afficher les résultats sur l'ecran d'affichage de contexte

            HashMap<String, ArrayList<String>> allContexteOf =controleurListeContexte.getAllContexteOf(getMotRecherche(), getRayon());
            this.ecranAffichageContexte.afficherResultat(this.motRecherche, allContexteOf);
            /*for (String titreTexte: allContexteOf.keySet()){
                System.out.println(titreTexte);
                for (String contexte: allContexteOf.get(titreTexte)){
                    System.out.println("\t" + contexte);
                }
            }*/
        }
    }
}
