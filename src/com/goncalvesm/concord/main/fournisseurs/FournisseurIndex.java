package com.goncalvesm.concord.main.fournisseurs;

import com.goncalvesm.concord.main.index.ControleurListeIndex;
import com.goncalvesm.concord.main.ListeMotsVide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FournisseurIndex extends Fournisseur {


    private ControleurListeIndex controleurListeIndex;


    public FournisseurIndex(ControleurListeIndex controleurListeIndex, String titre, String contenu){
        super();
        this.controleurListeIndex = controleurListeIndex;
        this.titre = titre;
        this.contenu = contenu;
    }

    //Demander au controleur de la liste des index d'ajouter un index
    private void ajouterIndex(String titre, String mot, Integer position){
        //System.out.println(titre + "--> Index: " + mot + ", position = " + position);
        this.controleurListeIndex.addIndex(titre, mot, position);
    }

    //TODO: Régler problème où écrire "s'il-vous-plaît" ne donne pas toute l'expression.(ainsi que pour d'autres expressions du même genre)

    @Override
    public void generer() {

        //Séparation des mots en incluant les caractères UNICODE, et en excluant certains ponctuations (on laisse
        ArrayList<String> motsListe = Stream.of(this.contenu.split("[^\\p{IsAlphabetic}&^\\-']+")).collect(Collectors.toCollection(ArrayList::new));

        try {
            //Suppression des mots vides
            motsListe.removeAll(ListeMotsVide.getInstance().getMotsVide());
            HashSet<String> mots = new HashSet<>(motsListe);
            //int indexOfMinTexteList = 0;
            for (String motActuel: mots){
                Matcher m = Pattern.compile("\\b" + motActuel + "\\b").matcher(this.contenu);
                while (m.find()) {
                    //Enregistrement de la position
                    this.ajouterIndex(this.titre, motActuel, m.start());
                }
            }

            /*int indexOfMinTexte = 0;
            int indexOfMinTexteList = 0;
            while (indexOfMinTexteList < minTexteList.size()){
                //Mot que l'on recherche actuellement
                String motActuel = minTexteList.get(indexOfMinTexteList);

                //Parcours des mots non-vide du texte et ajout dans à liste d'index
                while (indexOfMinTexte != -1){
                    //Afin de savoir si on a déjà commencé la recherche pour le mot actuel
                    if (indexOfMinTexte == 0){
                        indexOfMinTexte = minTexte.indexOf(motActuel);
                    }
                    else {
                        indexOfMinTexte = minTexte.indexOf(motActuel, indexOfMinTexte + 1);
                    }
                    //Ajout de l'index si il existe.
                    if (indexOfMinTexte != -1){
                        ajouterIndex(this.titre, motActuel, Integer.valueOf(indexOfMinTexte));
                    }

                }
                indexOfMinTexte = 0;
                indexOfMinTexteList++;
            }
*/
            //System.out.println(minTexte.indexOf(minTexteList.get(20)));
            System.out.println("FournisseurIndex fini pour " + titre );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
