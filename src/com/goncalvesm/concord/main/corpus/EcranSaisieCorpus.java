package com.goncalvesm.concord.main.corpus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class EcranSaisieCorpus extends JPanel implements FocusListener{

    private ControleurCorpus controleurCorpus;

    private JButton confirmerBtn;
    private JTextField titre1, titre2, titre3;
    private final String titrePlaceholder, textePlaceholder;
    private JTextArea texte1, texte2, texte3;
    
    //Pour la présentation
    private JSeparator separator;
    private JSeparator separator_1;
    private JSeparator separator_2;
    private JSeparator separator_3;
    private JSeparator separator_4;
    private JSeparator separator_5;
    private JSeparator separator_6;

    public EcranSaisieCorpus(ControleurCorpus controleurCorpus){
        super();
        this.controleurCorpus = controleurCorpus;
        titrePlaceholder = "Titre";
        textePlaceholder = "Écrivez le texte";

        init();
    }

    private void init(){
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        gridBagLayout.rowHeights = new int[]{17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWidths = new int[]{20, 421, 20};
        this.setLayout(gridBagLayout);
        
        separator_3 = new JSeparator();
        GridBagConstraints gbc_separator_3 = new GridBagConstraints();
        gbc_separator_3.insets = new Insets(0, 0, 5, 5);
        gbc_separator_3.gridx = 1;
        gbc_separator_3.gridy = 0;
        add(separator_3, gbc_separator_3);
        
        
        
        JLabel presentationTxt1= new JLabel("Texte 1");
        
        GridBagConstraints gbc_presentationTxt1 = new GridBagConstraints();
        gbc_presentationTxt1.anchor = GridBagConstraints.SOUTHWEST;
        gbc_presentationTxt1.insets = new Insets(0, 0, 5, 5);
        gbc_presentationTxt1.gridx = 1;
        gbc_presentationTxt1.gridy = 1;
        this.add(presentationTxt1, gbc_presentationTxt1);
        
        titre1 = new JTextField();
        presentationTxt1.setLabelFor(titre1);
        GridBagConstraints gbc_titre1 = new GridBagConstraints();
        gbc_titre1.fill = GridBagConstraints.BOTH;
        gbc_titre1.insets = new Insets(0, 0, 5, 5);
        gbc_titre1.gridx = 1;
        gbc_titre1.gridy = 2;
        //Activer le placeholder
        setTitrePlaceholder(titre1);
        this.add(titre1, gbc_titre1);
        
        texte1 = new JTextArea();
        texte1.setRows(6);
        texte1.setWrapStyleWord(true);
        texte1.setLineWrap(true);
        JScrollPane texte1SP = new JScrollPane(texte1);
        GridBagConstraints gbc_texte1SP = new GridBagConstraints();
        gbc_texte1SP.gridheight = 2;
        gbc_texte1SP.fill = GridBagConstraints.BOTH;
        gbc_texte1SP.insets = new Insets(0, 0, 5, 5);
        gbc_texte1SP.gridx = 1;
        gbc_texte1SP.gridy = 3;
        //Activer le placeholder
        setTextePlaceholder(texte1);
        this.add(texte1SP, gbc_texte1SP);
        
        separator = new JSeparator();
        separator.setForeground(SystemColor.controlShadow);
        separator.setBackground(Color.WHITE);
        GridBagConstraints gbc_separator = new GridBagConstraints();
        gbc_separator.fill = GridBagConstraints.BOTH;
        gbc_separator.insets = new Insets(0, 0, 5, 5);
        gbc_separator.gridx = 1;
        gbc_separator.gridy = 5;
        add(separator, gbc_separator);
        
        JLabel presentationTxt2 = new JLabel("Texte 2");
        
        GridBagConstraints gbc_presentationTxt2 = new GridBagConstraints();
        gbc_presentationTxt2.anchor = GridBagConstraints.SOUTHWEST;
        gbc_presentationTxt2.insets = new Insets(0, 0, 5, 5);
        gbc_presentationTxt2.gridx = 1;
        gbc_presentationTxt2.gridy = 6;
        this.add(presentationTxt2, gbc_presentationTxt2);
       
        titre2 = new JTextField();
        presentationTxt2.setLabelFor(titre2);
        GridBagConstraints gbc_titre2 = new GridBagConstraints();
        gbc_titre2.fill = GridBagConstraints.HORIZONTAL;
        gbc_titre2.insets = new Insets(0, 0, 5, 5);
        gbc_titre2.gridx = 1;
        gbc_titre2.gridy = 7;
        //Activer le placeholder
        setTitrePlaceholder(titre2);
        this.add(titre2, gbc_titre2);

        texte2 = new JTextArea();
        texte2.setRows(8);
        texte2.setWrapStyleWord(true);
        texte2.setLineWrap(true);
        //Activer le placeholder
        setTextePlaceholder(texte2);
        JScrollPane texte2SP = new JScrollPane(texte2);
        
        GridBagConstraints gbc_texte2SP = new GridBagConstraints();
        gbc_texte2SP.gridheight = 2;
        gbc_texte2SP.fill = GridBagConstraints.BOTH;
        gbc_texte2SP.insets = new Insets(0, 0, 5, 5);
        gbc_texte2SP.gridx = 1;
        gbc_texte2SP.gridy = 8;
        this.add(texte2SP, gbc_texte2SP);
        
        separator_4 = new JSeparator();
        GridBagConstraints gbc_separator_4 = new GridBagConstraints();
        gbc_separator_4.gridheight = 17;
        gbc_separator_4.insets = new Insets(0, 0, 5, 5);
        gbc_separator_4.gridx = 0;
        gbc_separator_4.gridy = 0;
        add(separator_4, gbc_separator_4);
        
        separator_5 = new JSeparator();
        GridBagConstraints gbc_separator_5 = new GridBagConstraints();
        gbc_separator_5.gridheight = 17;
        gbc_separator_5.insets = new Insets(0, 0, 5, 0);
        gbc_separator_5.gridx = 2;
        gbc_separator_5.gridy = 0;
        add(separator_5, gbc_separator_5);
        
        separator_1 = new JSeparator();
        GridBagConstraints gbc_separator_1 = new GridBagConstraints();
        gbc_separator_1.fill = GridBagConstraints.BOTH;
        gbc_separator_1.insets = new Insets(0, 0, 5, 5);
        gbc_separator_1.gridx = 1;
        gbc_separator_1.gridy = 10;
        add(separator_1, gbc_separator_1);
        
        
        JLabel presentationTxt3 = new JLabel("Texte 3");
        GridBagConstraints gbc_presentationTxt3 = new GridBagConstraints();
        gbc_presentationTxt3.anchor = GridBagConstraints.SOUTHWEST;
        gbc_presentationTxt3.insets = new Insets(0, 0, 5, 5);
        gbc_presentationTxt3.gridx = 1;
        gbc_presentationTxt3.gridy = 11;
        this.add(presentationTxt3, gbc_presentationTxt3);
        
        titre3 = new JTextField();
        presentationTxt3.setLabelFor(titre3);
        GridBagConstraints gbc_titre3 = new GridBagConstraints();
        gbc_titre3.fill = GridBagConstraints.HORIZONTAL;
        gbc_titre3.insets = new Insets(0, 0, 5, 5);
        gbc_titre3.gridx = 1;
        gbc_titre3.gridy = 12;
        //Activer le placeholder
        setTitrePlaceholder(titre3);
        this.add(titre3, gbc_titre3);
        
        texte3 = new JTextArea();
        texte3.setRows(8);
        texte3.setLineWrap(true);
        JScrollPane texte3SP = new JScrollPane(texte3);
        GridBagConstraints gbc_texte3SP = new GridBagConstraints();
        gbc_texte3SP.gridheight = 2;
        gbc_texte3SP.fill = GridBagConstraints.BOTH;
        gbc_texte3SP.insets = new Insets(0, 0, 5, 5);
        gbc_texte3SP.gridx = 1;
        gbc_texte3SP.gridy = 13;
        //Activer le placeholder
        setTextePlaceholder(texte3);
        this.add(texte3SP, gbc_texte3SP);
        
        confirmerBtn = new JButton("Confirmer");
        confirmerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Verification de la validité des textes.
                Boolean txt1NotEmpty = !(titre1.getText().trim().isEmpty() && texte1.getText().trim().isEmpty()) && !(titre1.getText().trim().equals(titrePlaceholder) && texte1.getText().trim().equals(textePlaceholder));
                Boolean txt2NotEmpty = !(titre2.getText().trim().isEmpty() && texte2.getText().trim().isEmpty()) && !(titre2.getText().trim().equals(titrePlaceholder) && texte2.getText().trim().equals(textePlaceholder));;
                Boolean txt3NotEmpty = !(titre3.getText().trim().isEmpty() && texte3.getText().trim().isEmpty())  && !(titre3.getText().trim().equals(titrePlaceholder) && texte3.getText().trim().equals(textePlaceholder));;

                if ( txt1NotEmpty || txt2NotEmpty || txt3NotEmpty  ){
                    //Envoie des textes
                    ArrayList<String> titres = new ArrayList<>();
                    ArrayList<String> textes = new ArrayList<>();

                    if (txt1NotEmpty){
                        titres.add(titre1.getText().trim());
                        textes.add(texte1.getText().trim().toLowerCase());
                        titre1.setEditable(false);
                        texte1.setEditable(false);
                    }

                    if (txt2NotEmpty){
                        titres.add(titre2.getText().trim());
                        textes.add(texte2.getText().trim().toLowerCase());
                        titre2.setEditable(false);
                        texte2.setEditable(false);
                    }

                    if (txt3NotEmpty){
                        titres.add(titre2.getText().trim());
                        textes.add(texte3.getText().trim().toLowerCase());
                        titre3.setEditable(false);
                        texte3.setEditable(false);
                    }

                    //Envoie des textes au controleur
                    controleurCorpus.setCorpus(titres, textes);
                    desactiverConfirmerBtn();
                }
            }
        });
        
        separator_2 = new JSeparator();
        GridBagConstraints gbc_separator_2 = new GridBagConstraints();
        gbc_separator_2.fill = GridBagConstraints.BOTH;
        gbc_separator_2.insets = new Insets(0, 0, 5, 5);
        gbc_separator_2.gridx = 1;
        gbc_separator_2.gridy = 15;
        add(separator_2, gbc_separator_2);
        
        GridBagConstraints gbc_confirmerBtn = new GridBagConstraints();
        gbc_confirmerBtn.insets = new Insets(0, 0, 5, 5);
        gbc_confirmerBtn.anchor = GridBagConstraints.EAST;
        gbc_confirmerBtn.gridx = 1;
        gbc_confirmerBtn.gridy = 16;
        this.add(confirmerBtn, gbc_confirmerBtn);
        
        separator_6 = new JSeparator();
        GridBagConstraints gbc_separator_6 = new GridBagConstraints();
        gbc_separator_6.insets = new Insets(0, 0, 0, 5);
        gbc_separator_6.gridx = 1;
        gbc_separator_6.gridy = 17;
        add(separator_6, gbc_separator_6);
    }

    public void desactiverConfirmerBtn(){
        this.confirmerBtn.setEnabled(false);
    }

    public void activerConfirmerBtn(){
        this.confirmerBtn.setEnabled(true);
    }

    //Activer le placeholder pour les éléments titres
    private void setTitrePlaceholder(JTextField titreField){
        titreField.setForeground(Color.GRAY);
        titreField.setText(titrePlaceholder);
        titreField.addFocusListener(this);
    }

    //Activer le placeholder pour les éléments texte
    private void setTextePlaceholder(JTextArea texteArea){
        texteArea.setForeground(Color.GRAY);
        texteArea.setText(textePlaceholder);
        texteArea.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        Object source = e.getSource();
        if (source instanceof JTextField){
            JTextField titreField = (JTextField) source;
            if (titreField.getText().equals(titrePlaceholder)) {
                titreField.setText("");
                titreField.setForeground(Color.BLACK);
            }
        }
        else if (source instanceof JTextArea){
            JTextArea texteArea = (JTextArea) source;
            if (texteArea.getText().equals(textePlaceholder)) {
                texteArea.setText("");
                texteArea.setForeground(Color.BLACK);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        Object source = e.getSource();
        if (source instanceof JTextField){
            JTextField titreField = (JTextField) source;
            if (titreField.getText().isEmpty()) {
                titreField.setForeground(Color.GRAY);
                titreField.setText(titrePlaceholder);
            }
        }
        else if (source instanceof JTextArea){
            JTextArea texteArea = (JTextArea) source;
            if (texteArea.getText().isEmpty()) {
                texteArea.setForeground(Color.GRAY);
                texteArea.setText(textePlaceholder);
            }
        }
    }
}
