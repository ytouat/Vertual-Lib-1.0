/*************************************
 *	Nom: FenetrePersoLivre.java  *
 *	Auteur: Youcef Touat         *
 ************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class FenetrePersoLivre extends JDialog {
	private Livre  unLivre = new Livre();
	private JTextField titre, categorie, prix, codeAuteur, codeLivre, nbPages;
	private JLabel titreLabel, categorieLabel, prixLabel, codeAuteurLabel, codeLivreLabel, nbPagesLabel;
	
	public FenetrePersoLivre(){
	  }
	
	public FenetrePersoLivre(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(300, 470);
	    	this.setLocationRelativeTo(null);
	    	this.setResizable(false);
	    	this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    	this.initComponent();
	}

	public Livre FenetreLivre(){
		    this.setVisible(true);      
		    return this.unLivre;      
	}
	  private void initComponent(){
		    
	            //Le titre
		    TitledBorder titreBordure;
		    JPanel panTitre = new JPanel();
		    panTitre.setBackground(Color.white);
		    titre = new JTextField();
		    titre.setPreferredSize(new Dimension(150, 25));
		    titreBordure = BorderFactory.createTitledBorder("Titre");
		    panTitre.setBorder(titreBordure);
		    titreBordure.setTitleJustification(TitledBorder.CENTER);
		    panTitre.add(titre);
	 
		    //Code auteur
		    JPanel panCodeAuteur = new JPanel();
		    panCodeAuteur.setBackground(Color.white);
		    codeAuteur = new JTextField();
		    codeAuteur.setPreferredSize(new Dimension(150, 25));
		    titreBordure = BorderFactory.createTitledBorder("Code auteur");
		    panCodeAuteur.setBorder(titreBordure);
		    titreBordure.setTitleJustification(TitledBorder.CENTER);
		    panCodeAuteur.add(codeAuteur);
		   
		    //Categorie
		    JPanel panCategorie = new JPanel();
		    panCategorie.setBackground(Color.white);
		    categorie = new JTextField();
		    titreBordure = BorderFactory.createTitledBorder("Categorie");
		    categorie.setPreferredSize(new Dimension(150, 25));
		    panCategorie.setBorder(titreBordure);
		    titreBordure.setTitleJustification(TitledBorder.CENTER);
		    panCategorie.add(categorie);
		    
		    //le prix
		    JPanel panPrix = new JPanel();
		    panPrix.setBackground(Color.white);
		    prix = new JTextField();
		    prix.setPreferredSize(new Dimension(150, 25));
		    titreBordure = BorderFactory.createTitledBorder("Prix");
		    titreBordure.setTitleJustification(TitledBorder.CENTER);
		    panPrix.setBorder(titreBordure);
		    panPrix.add(prix);
		    
		    //Code Livre
		    JPanel panCodeLivre = new JPanel();
		    panCodeLivre.setBackground(Color.white);
		    codeLivre = new JTextField();
		    codeLivre.setPreferredSize(new Dimension(150, 25));
		    titreBordure = BorderFactory.createTitledBorder("Code livre");
		    titreBordure.setTitleJustification(TitledBorder.CENTER);
		    panCodeLivre.setBorder(titreBordure);
		    panCodeLivre.add(codeLivre);
		 
		    //Nombre de Pages
		    JPanel panNbPages = new JPanel();
		    panNbPages.setBackground(Color.white);
		    nbPages = new JTextField();
		    nbPages.setPreferredSize(new Dimension(150, 25));
		    titreBordure = BorderFactory.createTitledBorder("Nombre de pages");
		    titreBordure.setTitleJustification(TitledBorder.CENTER);
		    panNbPages.setBorder(titreBordure);
		    panNbPages.add(nbPages);
		    		   
		    JPanel content = new JPanel();
		    content.setBackground(Color.white);
		    content.add(panTitre);
		    content.add(panCodeLivre);
		    content.add(panCodeAuteur);
		    content.add(panCategorie);
		    content.add(panNbPages);
		    content.add(panPrix);

		    JPanel control = new JPanel();
		    final JButton okBouton = new JButton("OK");
		    
		    okBouton.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e) {
		    	  JOptionPane jop = new JOptionPane();
		    	  try { 
		    		  unLivre = new Livre(titre.getText(), 
						      Integer.parseInt(codeLivre.getText()), 
		    				      Integer.parseInt(codeAuteur.getText()), 
		    				      categorie.getText(),
		    				      Integer.parseInt(nbPages.getText()),
		    				      Double.parseDouble(prix.getText()));
		    		  setVisible(false);
		    		  } catch (NumberFormatException ex) {  
		    		     jop.showMessageDialog(null, " Entrez toutes les informations", 
		      						 " Erreur", JOptionPane.ERROR_MESSAGE);
		    		  }
		      }
		    });

		    JButton cancelBouton = new JButton("Annuler");
		    cancelBouton.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e) {
		        setVisible(false);
		      }      
		    });

		    control.add(okBouton);
		    control.add(cancelBouton);
		    this.getContentPane().add(content, BorderLayout.CENTER);
		    this.getContentPane().add(control, BorderLayout.SOUTH);
		  }  
}
