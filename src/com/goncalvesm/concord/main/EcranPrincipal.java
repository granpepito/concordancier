package com.goncalvesm.concord.main;

import com.goncalvesm.concord.main.contexte.ControleurListeContexte;
import com.goncalvesm.concord.main.contexte.CoordinateurContexte;
import com.goncalvesm.concord.main.contexte.EcranAffichageContexte;
import com.goncalvesm.concord.main.contexte.EcranSaisieContexte;
import com.goncalvesm.concord.main.corpus.ControleurCorpus;
import com.goncalvesm.concord.main.corpus.Corpus;
import com.goncalvesm.concord.main.corpus.EcranSaisieCorpus;
import com.goncalvesm.concord.main.index.ControleurListeIndex;
import com.goncalvesm.concord.main.index.CoordinateurIndex;

import javax.swing.*;
import java.awt.*;

public class EcranPrincipal extends JFrame {

    private EcranSaisieCorpus ecranSaisieCorpus;
    private EcranSaisieContexte ecranSaisieContexte;
    private EcranAffichageContexte ecranAffichageContexte;

    private JSplitPane splitPane;
    private JSplitPane rightSplitPane;


    public EcranPrincipal(ControleurCorpus controleurCorpus, ControleurListeIndex controleurListeIndex, ControleurListeContexte controleurListeContexte){
        super();
    	ecranSaisieCorpus = new EcranSaisieCorpus(controleurCorpus);
        ecranSaisieContexte = new EcranSaisieContexte(controleurListeContexte.getCoordinateurContexte());
        ecranAffichageContexte = new EcranAffichageContexte();

        //Ajout de l'écran de recherche de contexte afin que le coordinateur puisse activer les champs de recherche.
        controleurCorpus.getCoordinateurIndex().setEcranSaisieContexte(ecranSaisieContexte);
        controleurCorpus.getCoordinateurIndex().setEcranSaisieCorpus(ecranSaisieCorpus);

        //Ajout de l'écran d'affichage pour que le coordinateur puisse envoyer les résultats de la recherche de contexte.
        controleurListeContexte.getCoordinateurContexte().setEcranAffichageContexte(ecranAffichageContexte);
        controleurListeContexte.getCoordinateurContexte().setControleurListeIndex(controleurListeIndex);
        controleurListeContexte.getCoordinateurContexte().setControleurCorpus(controleurCorpus);
        //controleurListeContexte.getCoordinateurContexte().setControleurListeContexte(controleurListeContexte);
        init();
    }

    private void init(){
        System.out.println("Initialisation du concordancier...");

        this.setTitle("Concordancier - Projet POO");
        //this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        splitPane = new JSplitPane();

        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(ecranSaisieCorpus);

        rightSplitPane = new JSplitPane();
        rightSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        rightSplitPane.setTopComponent(ecranSaisieContexte);
        rightSplitPane.setBottomComponent(ecranAffichageContexte);

        splitPane.setRightComponent(rightSplitPane);

        this.setContentPane(splitPane);
        this.pack();
    }


    public static void main(String[] args) {
        //Initialisation
        ControleurListeIndex controleurListeIndex = new ControleurListeIndex();

        ControleurListeContexte controleurListeContexte = new ControleurListeContexte();
        CoordinateurContexte coordinateurContexte = new CoordinateurContexte(controleurListeContexte);
        controleurListeContexte.setCoordinateurContexte(coordinateurContexte);

        CoordinateurIndex coordinateurIndex = new CoordinateurIndex(controleurListeIndex);
        ControleurCorpus controleurCorpus = new ControleurCorpus(new Corpus(), coordinateurIndex);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(() -> new EcranPrincipal(controleurCorpus, controleurListeIndex, controleurListeContexte).setVisible(true));
    }
}
