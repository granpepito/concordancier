package com.goncalvesm.concord.main.corpus;

public class Texte {

    private String titre, contenu;

    public Texte(){
        titre = "";
        contenu = "";
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Boolean equals(String titre){
        if (this.titre.equals(titre)){
            return true;
        }
        return false;
    }
}
