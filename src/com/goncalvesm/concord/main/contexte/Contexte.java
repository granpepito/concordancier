package com.goncalvesm.concord.main.contexte;

import java.util.ArrayList;

public class Contexte {

    //Contexte pour un mot (morceau précédent puis suivant le mot voulu)
    private ArrayList<String> prePost;
    private int rayon;

    public Contexte(){
        prePost = new ArrayList<>();
        rayon = 20;
    }

    public Contexte(String pre, String post){
        prePost = new ArrayList<>();
        rayon = 20;
        setPrePost(pre, post);
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public void setPrePost(String pre, String post) {
        this.prePost.add(pre);
        this.prePost.add(post);
    }

    public ArrayList<String> getPrePost() {
        return prePost;
    }

    public String toString(String motPrincipal){
        return this.prePost.get(0) + motPrincipal + this.prePost.get(1);
    }

}
