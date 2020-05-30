package com.goncalvesm.concord.main.contexte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EcranSaisieContexte extends JPanel implements ActionListener{

    private JTextField searchBar;
    private JButton searchBtn;
    private CoordinateurContexte coordinateurContexte;
    private int rayon;
    private final String searchBarPlaceholder = "Rechercher un mot", searchBtnPlaceholder = "Rechercher";
    private JLabel lblRayon;
    

    public EcranSaisieContexte(CoordinateurContexte coordinateurContexte){
        super();
    	this.coordinateurContexte = coordinateurContexte;
    	this.rayon = 20;
        init();
    }

    private void init() {
    	this.setLayout(new FlowLayout());
    	
    	initSearchBar();
        initSearchBtn();
        //desactiverRecherche();
    }
    
    //Initialisation de la barre de recherche
    private void initSearchBar(){
        this.searchBar = new JTextField();
        searchBar.setColumns(40);
        

        //Activer le placeholder
        this.searchBar.setForeground(Color.GRAY);
        this.searchBar.setText(searchBarPlaceholder);
        this.searchBar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBar.getText().equals(searchBarPlaceholder)) {
                    searchBar.setText("");
                    searchBar.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchBar.getText().isEmpty()) {
                    searchBar.setForeground(Color.GRAY);
                    searchBar.setText(searchBarPlaceholder);
                }
            }
        });

        this.searchBar.addActionListener(this);
        this.add(searchBar);
        
        lblRayon = new JLabel("Rayon");
        add(lblRayon);

        String[] choixRayon = {"5", "10", "20"};
        JComboBox comboBoxRayon = new JComboBox(choixRayon);
        comboBoxRayon.setSelectedIndex(2);
        comboBoxRayon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cbRayon = (JComboBox)e.getSource();
                try {
                    rayon = Integer.parseInt(cbRayon.getSelectedItem().toString());
                }
                catch (Exception exception){
                    System.out.println("EcranSaisieContexte comboBoxRayon: " + exception.getMessage());
                }
            }
        });
        this.add(comboBoxRayon);
    }

    //Initialisation du bouton de recherche
    private void initSearchBtn(){
        this.searchBtn = new JButton(searchBtnPlaceholder);
        this.searchBtn.addActionListener(this);
        this.add(searchBtn);
    }

    //Permettre la recherche
    public void activerRecherche(){
        this.searchBar.setEditable(true);
        this.searchBtn.setEnabled(true);
    }

    //Empêcher la recherche si il n'y pas de mot à rechercher
    public void desactiverRecherche(){

        this.searchBar.setEditable(false);
        this.searchBtn.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!searchBar.getText().isEmpty() && !searchBar.equals(searchBarPlaceholder)){
            String motRecherche = searchBar.getText().trim();

            /*try{*/
                coordinateurContexte.startRecherche(motRecherche, rayon);
            /*}
            catch (NullPointerException exception){
                //System.out.println("EcranSaisieContexte: aucun texte n'a été entré.");
            }*/
        }
        else {
            System.out.println("EcranSaisieContexte: aucun texte n'a été entré.");
        }
    }
}
