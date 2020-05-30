package com.goncalvesm.concord.main.contexte;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EcranAffichageContexte extends JPanel {

	private HTMLEditorKit htmlEditorKit;
	private Document document;
	private StyleSheet styleSheet;
	private JEditorPane contextePane;
    private JTextField occurenceField;

    public EcranAffichageContexte(){
    	super();
    	init();
    }

    private void init(){
    	GridBagLayout gridBagLayout = new GridBagLayout();
    	gridBagLayout.columnWidths = new int[]{17, 417, 35, 18, 0};
    	gridBagLayout.rowHeights = new int[]{26, 0, 0, 375, 0, 0};
    	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
    	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
    	setLayout(gridBagLayout);
    	
    	JLabel lblOccurences = new JLabel("Occurences:");
    	GridBagConstraints gbc_lblOccurences = new GridBagConstraints();
    	gbc_lblOccurences.fill = GridBagConstraints.VERTICAL;
    	gbc_lblOccurences.anchor = GridBagConstraints.EAST;
    	gbc_lblOccurences.insets = new Insets(0, 0, 5, 5);
    	gbc_lblOccurences.gridx = 1;
    	gbc_lblOccurences.gridy = 1;
    	add(lblOccurences, gbc_lblOccurences);

    	occurenceField = new JTextField();
    	occurenceField.setHorizontalAlignment(SwingConstants.RIGHT);
    	occurenceField.setEditable(false);
    	occurenceField.setText("0");
    	GridBagConstraints gbc_textField = new GridBagConstraints();
    	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
    	gbc_textField.insets = new Insets(0, 0, 5, 5);
    	gbc_textField.gridx = 2;
    	gbc_textField.gridy = 1;
    	add(occurenceField, gbc_textField);
    	occurenceField.setColumns(3);
    	
    	JLabel lblContextes = new JLabel("Contextes:");
    	GridBagConstraints gbc_lblContextes = new GridBagConstraints();
    	gbc_lblContextes.fill = GridBagConstraints.VERTICAL;
    	gbc_lblContextes.anchor = GridBagConstraints.WEST;
    	gbc_lblContextes.insets = new Insets(0, 0, 5, 5);
    	gbc_lblContextes.gridx = 1;
    	gbc_lblContextes.gridy = 2;
    	add(lblContextes, gbc_lblContextes);
    	
    	contextePane = new JEditorPane();
    	contextePane.setContentType("text/html");
		htmlEditorKit = new HTMLEditorKit();
		contextePane.setEditorKit(htmlEditorKit);
		document = contextePane.getDocument();
		contextePane.setEditable(false);

		//Ajout des règles de style
		styleSheet = htmlEditorKit.getStyleSheet();
		styleSheet.addRule(".contexte { display: block; }");
		//styleSheet.addRule(".occurence { float: right; }");
		//styleSheet.addRule(".liste-contextes { float: left; }");
		styleSheet.addRule(".nbr-contexte { color: red; }");

		JScrollPane textPaneSP = new JScrollPane(contextePane);
    	GridBagConstraints gbc_textPaneSP = new GridBagConstraints();
    	gbc_textPaneSP.gridwidth = 2;
    	gbc_textPaneSP.insets = new Insets(0, 0, 5, 5);
    	gbc_textPaneSP.fill = GridBagConstraints.BOTH;
    	gbc_textPaneSP.gridx = 1;
    	gbc_textPaneSP.gridy = 3;
    	textPaneSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	textPaneSP.setMinimumSize(new Dimension(300, 100));
    	add(textPaneSP, gbc_textPaneSP);


    }

    //TODO: Possibilité de le faire directement dans la classe Contexte, pourrait éviter de faire des méthodes en plus
	private String enGras(String mot, String contexte){
		String enGras = "<b> " + mot + " </b>";
		contexte = String.join(enGras, contexte.split(mot));

    	return contexte;
	}

	public void afficherErreur(String motRecherche){
    	contextePane.setText("");
    	occurenceField.setText("0");
    	StringBuffer messageErreur = new StringBuffer("<html>");
    	messageErreur.append("<p>Le mot <b>" + motRecherche + "</b> n'est pas trouvable.</p>");
    	messageErreur.append("<p>Il est possible que le mot n'ait pas été indexé.</p>");

		try {
			htmlEditorKit.insertHTML((HTMLDocument) document, 0, messageErreur.toString(), 0, 0, HTML.Tag.HTML);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//TODO: Utiliser les HTMLDocument, HTMLEditorKit etc.
	public void afficherResultat(String motRecherche, HashMap<String, ArrayList<String>> listesContextes){
		System.out.println("EcranAffichageContexte: Affichage des contextes du mot '" + motRecherche + "'.");
       	contextePane.setText("");

        int occurenceTotale = 0;

		StringBuffer resultat = new StringBuffer("<html>");
        resultat.append("<body>");

        for (String titreTexte: listesContextes.keySet()) {

        	ArrayList<String> contextesFrom = listesContextes.get(titreTexte);
        	int nbrContexteFrom = contextesFrom.size();
        	occurenceTotale+= nbrContexteFrom;

        	resultat.append("<div class='contexte'><p>" + titreTexte + " (<span class='nbr-contexte'>" + nbrContexteFrom + "</span>):</p>");
			//resultat.append("<p class='occurence'>" + contextesFrom.size() + "</p>");
			resultat.append("<ul class='liste-contextes'>");
        	for (String contexte: contextesFrom) {
        		//Ajout des contextes dans la liste
        		resultat.append("<li><b>... </b>" + enGras(motRecherche, contexte) + "<b> ...</b></li>");
        	}
        	resultat.append("</ul></div>");
        }

        resultat.append("</body></html>");


        occurenceField.setText("" + occurenceTotale);
        //contextePane.setText(resultat.toString());
		try {
			htmlEditorKit.insertHTML((HTMLDocument) document, 0, resultat.toString(), 0, 0, HTML.Tag.HTML);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
