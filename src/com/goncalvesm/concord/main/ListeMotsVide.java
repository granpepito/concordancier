package com.goncalvesm.concord.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ListeMotsVide {

    //Source mots vide
    //  http://cerig.pagora.grenoble-inp.fr/Recherche/mots-vides.htm
    //  https://www.webrankinfo.com/forum/t/liste-de-mots-vides-francais-pour-les-moteurs-de.56224/

    private static ListeMotsVide INSTANCE;
    private List<String> motsVide;

    public ListeMotsVide() throws IOException {
        motsVide = Files.readAllLines(Paths.get("src/com/goncalvesm/concord/motsvide.txt"));
    }

    public static synchronized ListeMotsVide getInstance() throws IOException {
        if (INSTANCE == null) {
            synchronized (ListeMotsVide.class) {
                if (INSTANCE == null)
                    INSTANCE = new ListeMotsVide();
            }
        }
        return INSTANCE;
    }

    public List<String> getMotsVide() {
        return motsVide;
    }
}
