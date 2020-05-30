package com.goncalvesm.concord.main.fournisseurs;

import com.goncalvesm.concord.main.contexte.Contexte;
import com.goncalvesm.concord.main.contexte.ControleurListeContexte;
import com.goncalvesm.concord.main.index.Index;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FournisseurContexte extends Fournisseur{

    private ControleurListeContexte controleurListeContexte;
    private int rayon = 20;
    private String contenuInverse;
    private String motRecherche;
    private Index indexes;

    public FournisseurContexte(ControleurListeContexte controleurListeContexte, String titre, String contenu, String motRecherche, Index indexes){
        super();
        this.controleurListeContexte = controleurListeContexte;
        this.titre = titre;
        this.contenu = contenu;
        this.motRecherche = motRecherche;
        this.indexes = indexes;
        this.contenuInverse = inverserTxt(this.contenu);
    }

    /*public int getRayon() {
        return rayon;
    }*/

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    //Calcul de la partie du contexte avant le mot recherché
    private ArrayList<String> pre(String motActuel, Index indexes, String textInverse, int rayon){
        ArrayList<String> result = new ArrayList<>();
        for (Integer indexOfMot: indexes.getPositions()){
            //A partir de la position du mot recherché (de manière opposée) on accumule des mots au niveau du rayon
            Matcher m = Pattern.compile("\\b[\\p{IsAlphabetic}'|-]+\\b").matcher(textInverse.substring(textInverse.length() - indexOfMot));
            int lastIndex = 0;
            int i = 0;
            while (m.find() && i < rayon + 1){
                lastIndex = m.end();
                i++;
            }
            //On inverse encore le texte afin d'obtenir une chaine lisible et coupe le texte afin de n'avoir que le mots voulu (rayon)
            String contextePre = inverserTxt(textInverse.substring(textInverse.length() - indexOfMot, textInverse.length() - indexOfMot + lastIndex));
            result.add(applatir(contextePre));
        }
        return result;
    }

    //Calcul de la partie du contexte apres le mot recherché
    private ArrayList<String> post(String motActuel, Index indexes, String text, int rayon){
        ArrayList<String> result = new ArrayList<>();
        for (Integer indexOfMot: indexes.getPositions()){
            //A partir de la position du mot recherché on accumule des mots au niveau du rayon
            Matcher m = Pattern.compile("\\b[\\p{IsAlphabetic}'|-]+\\b").matcher(text.substring(indexOfMot + motActuel.length()));
            int lastIndex = motActuel.length();
            int i = 0;
            while (m.find() && i < rayon ) {
                lastIndex = m.end();
                //System.out.println("Matching at: " + m.start());
                //System.out.println(motActuel + "+ = " + lastIndex);
                i++;
            }
            //On coupe le texte afin de n'avoir que ce qui est désiré (rayon)
            String contextePost = text.substring(indexOfMot + motActuel.length(), indexOfMot + motActuel.length() + lastIndex );
            result.add(applatir(contextePost));
        }
        return result;
    }

    //Inverser le texte afin de calculer le contexte (avant le mot recherché)
    private String inverserTxt(String text){
        if (text.isEmpty()){
            return text;
        }
        return inverserTxt(text.substring(1)) + text.charAt(0);
    }

    //Applatir une chaine de caractère qui possède des tabulations, nouvelles lignes.
    private String applatir(String contexte){
        //return String.join(" " , contexte.split("[\\n\\t\\r]"));
        return contexte.replace("\n", " ").replace("\r", " ").replace("\t", " ");
    }

    @Override
    public void generer() {
        ArrayList<String> preContexte = pre(this.motRecherche, this.indexes, this.contenuInverse, this.rayon);
        ArrayList<String> postContexte = post(this.motRecherche, this.indexes, this.contenu, this.rayon);

        ArrayList<Contexte> nouveauxContxt = new ArrayList<>();

        //Afin d'éviter le cas où un preContexte ou postContexte est plus petit que l'autre.
        int nbrMaxContexte = postContexte.size() <= preContexte.size() ? preContexte.size() : postContexte.size();

        for (int i = 0; i < nbrMaxContexte; i++){
            try {
                Contexte contexte = new Contexte(preContexte.get(i), postContexte.get(i));
                nouveauxContxt.add(contexte);
            }
            catch (ArrayIndexOutOfBoundsException e){
                if (preContexte.size() < postContexte.size()){
                    Contexte contexte = new Contexte("", postContexte.get(i));
                    nouveauxContxt.add(contexte);
                }
                else if (postContexte.size() < preContexte.size()){
                    Contexte contexte = new Contexte(preContexte.get(i), "");
                    nouveauxContxt.add(contexte);
                }
            }
        }

        controleurListeContexte.addContexte(this.titre, this.motRecherche, nouveauxContxt);
        System.out.println("FournisseurContexte fini pour " + this.titre);
    }
}
