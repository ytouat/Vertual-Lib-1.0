/***********************************
 *	Nom: FenetreRapport.java   *
 *	Auteur: Youcef Touat       *
 **********************************/
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class FenetreRapport extends JFrame implements ActionListener {
	private JTextArea texte;
	private JButton  RapportAuteurs, RapportLivres, boutonAnnuler;
	 
	//le constructeur
	public FenetreRapport(int l,int h, String titre) {
		  setTitle(titre);  
		  setLayout(new BorderLayout());
		  
		  RapportAuteurs = new JButton("Rapport par auteurs");
		  RapportLivres = new JButton("Rapport par livres");
		  boutonAnnuler = new JButton("Annuler");
		  RapportAuteurs.addActionListener(this);
		  RapportLivres.addActionListener(this);
		  boutonAnnuler.addActionListener(this);
		  
		  JPanel p = new JPanel();
		  p.add(Box.createVerticalStrut(50));
		  p.add(RapportAuteurs);
		  p.add(RapportLivres);
		  p.add(boutonAnnuler);
		  p.add(Box.createVerticalStrut(50));
		  texte = new JTextArea("Ceci est la zone pour afficher les "
		  						+ "\nrésultatset le contenu du fichier" );
		  JScrollPane areaScrollPane = new JScrollPane(texte);  //Barre de défilement
		    
		  areaScrollPane.setVerticalScrollBarPolicy(
                  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		  areaScrollPane.setHorizontalScrollBarPolicy(
                  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		  areaScrollPane.setPreferredSize(new Dimension(600, 500));

		   //La bordure
		   areaScrollPane.setBorder(
		   	BorderFactory.createCompoundBorder(
		        	BorderFactory.createCompoundBorder(null,
		        		BorderFactory.createEmptyBorder(5,5,5,5)),
		        			areaScrollPane.getBorder()));
	
	     	   add(p,BorderLayout.NORTH);  
		   JPanel leftPane = new JPanel();
		   leftPane.add(areaScrollPane);
		   add(leftPane,BorderLayout.CENTER);
		   setVisible(true);
	}
	public void actionPerformed (ActionEvent evt) {
		  if (evt.getSource() == RapportAuteurs) {
				String nomFichier = "parAuteur.txt";
				//LOCALISER le fichier à partir de son nom
				try {
				BufferedReader entree = new BufferedReader (new FileReader(nomFichier));
	             String ligne; 
	             texte.setText(""); // remise a zero du texte
	             while ((ligne = entree.readLine())!= null)
	             texte.append(ligne+"\n");
	             entree.close();
				}
			//intercepter l'erreur si le fichier n'existe pas
				catch ( IOException e) {
				System.out.println("Probleme d'ouverture du fichier " + nomFichier);
			}
		  }
		  if (evt.getSource() == RapportLivres) {
					String nomFichier = "parLivre.txt";
					//LOCALISER le fichier à partir de son nom
					try {
					BufferedReader entree = new BufferedReader (new FileReader(nomFichier));
		             String ligne;
		             texte.setText(""); // remise a zero du texte
		             while ((ligne = entree.readLine())!= null)
		               texte.append(ligne+"\n");
		             entree.close();
					}
					// intercepter l'erreur si le fichier n'existe pas
					catch ( IOException e) {
					System.out.println("Probleme d'ouverture du fichier " + nomFichier);
				}
		  }
		  if (evt.getSource() == boutonAnnuler) {
			  setVisible(false);
		  }
	}
}
