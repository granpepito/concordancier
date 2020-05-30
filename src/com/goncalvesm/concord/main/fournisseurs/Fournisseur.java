package com.goncalvesm.concord.main.fournisseurs;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

//Implémentation de listener  à l'aide de Eddie (https://stackoverflow.com/questions/702415/how-to-know-if-other-threads-have-finished)
public abstract class Fournisseur extends Thread {

    protected String titre, contenu;

    private final Set<AlerteThreadTermine> LISTENERS
            = new CopyOnWriteArraySet<>();
    public final void addListener(final AlerteThreadTermine listener) {
        LISTENERS.add(listener);
    }

    public final void removeListener(final AlerteThreadTermine listener) {
        LISTENERS.remove(listener);
    }

    private final void alerter() {
        for (AlerteThreadTermine listener : LISTENERS) {
            listener.alerterTerminaison(this);
        }
    }

    @Override
    public final void run() {
        try {
            generer();
        } finally {
            alerter();
        }
    }

    public abstract void generer();

}
