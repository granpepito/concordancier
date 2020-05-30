package com.goncalvesm.concord.main.fournisseurs;

//Implémentation de listener  à l'aide de Eddie (https://stackoverflow.com/questions/702415/how-to-know-if-other-threads-have-finished)
public interface AlerteThreadTermine {

    void alerterTerminaison(final Thread thread);
}
