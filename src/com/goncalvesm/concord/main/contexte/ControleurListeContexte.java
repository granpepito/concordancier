package com.goncalvesm.concord.main.contexte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class ControleurListeContexte {

    //Liste de contexte pour chaque texte en fonction de leur titre.
    private Hashtable<String, ListeContexte> listesContexte;
    private CoordinateurContexte coordinateurContexte;

    public ControleurListeContexte(){
        listesContexte = new Hashtable<>();
    }

    //Création d'une nouvelle liste de contexte
    /***
     * @param titreTexte est le titre du texte qui va posséder de nouveaux contextes
     */
    public ListeContexte newListeContexte(String titreTexte){
        ListeContexte nouveauxContextes = new ListeContexte();
        this.listesContexte.put(titreTexte, nouveauxContextes);
        return nouveauxContextes;
    }

    public CoordinateurContexte getCoordinateurContexte() {
        return coordinateurContexte;
    }

    //Obtention de la ListeContexte pour un texte
    private ListeContexte getListeContexteOfTexte(String titreTexte){
        if (containsTexte(titreTexte)){
            ListeContexte contextes = this.listesContexte.get(titreTexte);
            return contextes;
        }
        return null;
    }

    private String resizeContexte(String mot, Contexte contexte, int rayon){
        if (rayon != contexte.getRayon()){
            ArrayList<String> prePostContexte = contexte.getPrePost();

            String[] preList = prePostContexte.get(0).split(" ");
            String[] postList = prePostContexte.get(1).split(" ");

            StringBuffer pre = new StringBuffer();
            StringBuffer post = new StringBuffer();

            for (int i = 0; i <= rayon; i++ ){
                try {
                    pre.append(preList[preList.length - (rayon - i)] + " ");

                }
                catch (IndexOutOfBoundsException e){
                    //pre.append("");
                }
                try {
                    post.append(postList[i] + " ");
                }
                catch (IndexOutOfBoundsException e){
                    //post.append(" ");
                }
            }
            return pre.toString() + mot + post.toString();
        }

        return contexte.toString(mot);
    }

    public void setCoordinateurContexte(CoordinateurContexte coordinateurContexte) {
        this.coordinateurContexte = coordinateurContexte;
    }

    /**
     * @param titreTexte est le titre du texte
     * @param motPrincipal est le mot principal (recherché) du nouveau contexte
     * @param nouveauContexte est un contexte ayant déjà été créé */
    public void addContexte(String titreTexte, String motPrincipal, Contexte nouveauContexte){
        ListeContexte contextes = getListeContexteOfTexte(titreTexte);
        if (contextes != null){
            contextes.addContexte(motPrincipal, nouveauContexte);
        }
        else {
            ListeContexte nouveauxContextes = newListeContexte(titreTexte);
            nouveauxContextes.addContexte(motPrincipal, nouveauContexte);
        }
    }

    /**
     * @param titreTexte est le titre du texte
     * @param motPrincipal est le mot principal (recherché) du nouveau contexte
     * @param nouveauContextes est la nouvelle liste de contextes
     */
    public void addContexte(String titreTexte, String motPrincipal, ArrayList<Contexte> nouveauContextes){
        ListeContexte contextes = getListeContexteOfTexte(titreTexte);
        if (contextes != null){
            contextes.addContextes(motPrincipal, nouveauContextes);
        }
        else {
            ListeContexte nouveauxContextes = newListeContexte(titreTexte);
            nouveauxContextes.addContextes(motPrincipal, nouveauContextes);
        }
    }

    /*
     * @param titreTexte est le titre du texte
     * @param motPrincipal est le mot principal (recherché) du nouveau contexte
     * @param pre est la partie du contexte précédent le mot principal
     * @param post est la partie du contexte suivant le mot principal
    public void addContext(String titreTexte, String motPrincipal, String pre, String post){
        ListeContexte contextes = getListeContexteOfTexte(titreTexte);
        if (contextes != null){
            contextes.addContexte(motPrincipal, pre, post);
        }
        else {
            ListeContexte nouveauxContextes = newListeContexte(titreTexte);
            nouveauxContextes.addContexte(motPrincipal, pre, post);
        }
    }*/

  /*  public HashMap<String, ArrayList<String>> getAllContexteOf(String mot){
        HashMap<String, ArrayList<String>> contextesParTexte = new HashMap<>();
        for (String titreTexte: listesContexte.keySet()){
            ArrayList<String> contextes = listesContexte.get(titreTexte).getContexteOfToString(mot);
            if (contextes != null){
                contextesParTexte.put(titreTexte, contextes);
            }
        }
        return contextesParTexte;
    }*/

    public HashMap<String, ArrayList<String>> getAllContexteOf(String mot, int rayon){
        HashMap<String, ArrayList<String>> contextesParTexte = new HashMap<>();
        for (String titreTexte: listesContexte.keySet()){
            ArrayList<Contexte> contextes = listesContexte.get(titreTexte).getContextesOf(mot);
            if (contextes != null){
                ArrayList<String> resizedContextes = new ArrayList<>();
                for (Contexte contexte: contextes){
                    resizedContextes.add(resizeContexte(mot, contexte, rayon));
                }
                contextesParTexte.put(titreTexte, resizedContextes);
            }
        }
        return contextesParTexte;
    }

    //Savoir si un mot est présent dans une des liste de contexte
    public Boolean containsContexteOf(String mot){
        for (String titreTexte: listesContexte.keySet()){
            if (listesContexte.get(titreTexte).containsContexteOf(mot)){
                return true;
            }
        }
        return false;
    }

    public Boolean containsTexte(String titreTexte){
        return this.listesContexte.containsKey(titreTexte);
    }

    public Boolean containsTextes(ArrayList<String> titres){
        boolean result = false;
        for (String titireTexte: titres){
            result = containsTexte(titireTexte);
        }
        return result;
    }

}
