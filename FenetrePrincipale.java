/**************************************
 *	Nom: FenetrePrincipale.java   *
 *	Auteur: Youcef Touat          *
 *************************************/
import java.io.*;
import java.util.Collection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetrePrincipale extends JFrame implements ActionListener {

	//initialisation d'une base de données vide
	private Bdd BdDonnees  = new Bdd();

	//Barre de menu
	private JMenuBar menuBar;
	
	//items du menu Fichier
	private JMenuItem BaseAuteur = new JMenuItem("Importer fichier Auteur");
	private JMenuItem BaseLivre = new JMenuItem("Importer fichier Livre");
	private JMenuItem Quitter = new JMenuItem("Quitter");
	
	private JMenuItem addAuteur = new JMenuItem("Ajouter Auteur");
	private JMenuItem addLivre = new JMenuItem("Ajouter Livre");
	private JMenuItem colAuteur = new JMenuItem("Collection Auteur");
	private JMenuItem livre = new JMenuItem("Livre");
	private JMenuItem rapAuteurLivre = new JMenuItem("Genérer un rapport");
	
	private JMenuItem apropos = new JMenuItem("A propos de la bibliographie vertuelle");
	private JMenuItem help = new JMenuItem("Help");
	
	
	public FenetrePrincipale(int l, int h, String titre){
	   	    setTitle(titre);
		    setSize(l,h);
	    	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            setLocationRelativeTo(null);
	    
	            menuBar = new JMenuBar();
	            JMenuItem mi; // une reference de travail
	    
	      	    //Le Menu Fichier
		    JMenu fichier = new JMenu("Fichier");
		    fichier.setMnemonic('F');
		    menuBar.add(fichier);
		    
                    //Ajout des items aux menus
		  
		    //menu fichier
		    BaseAuteur.addActionListener(this);
		    fichier.add(BaseAuteur);
		     BaseAuteur.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 2));
		  
		     BaseLivre.addActionListener(this);
		     fichier.add(BaseLivre);
		     BaseLivre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, 2));
		  
		     Quitter.addActionListener(this);
		     fichier.add(Quitter);
		     Quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 2));
		  
		      //Menu  Edition
		      JMenu edition = new JMenu("Edition");
		      edition.setMnemonic('E');
		      menuBar.add(edition);
		  
		      //les sous menus 
		      JMenu Ajouter = new JMenu("Ajouter");
		      edition.add(Ajouter);
		  
		      //items du sous menu ajouter
		       addAuteur.addActionListener(this);
		       Ajouter.add(addAuteur);
		       addAuteur.setAccelerator(KeyStroke.getKeyStroke('t'));
		  
		       addLivre.addActionListener(this);
		       Ajouter.add(addLivre);
		       addLivre.setAccelerator(KeyStroke.getKeyStroke('v'));
		  
		        //sous menu chercher
		        JMenu chercher = new JMenu("Chercher");
		         edition.add(chercher);
		  
		         //items du sous menu chercher
		          colAuteur.addActionListener(this);
		          chercher.add(colAuteur);
		          colAuteur.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 8));
		  
		          livre.addActionListener(this);
		          chercher.add(livre);
		          livre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, 8));
		  
		          //menu rapport
		          JMenu rapport = new JMenu("Rapport");
		          rapport.setMnemonic('R');
		          menuBar.add(rapport);
		          //generation d'un rapport par auteur ou par livre
		          rapAuteurLivre.addActionListener(this);
		          rapport.add(rapAuteurLivre);
		          rapAuteurLivre.setAccelerator(KeyStroke.getKeyStroke("F2"));
		  
		          //menu Aide
		          JMenu aide = new JMenu("Aide");
		          aide.setMnemonic('D');
		          menuBar.add(aide);
		          help.addActionListener(this);
		          aide.add(help);
		          help.setAccelerator(KeyStroke.getKeyStroke("F4"));
		  
		          //menu a propos
		          JMenu aPropos = new JMenu("A propos");
		          aPropos.setMnemonic('P');
		          menuBar.add(aPropos);
		          apropos.addActionListener(this);
		          aPropos.add(apropos);
  		          apropos.setAccelerator(KeyStroke.getKeyStroke("F1"));
		  
		          //ajout du menu a la barre de menu
		          setJMenuBar(menuBar);
		          setVisible(true);
}

	public void actionPerformed(ActionEvent a) {
		JOptionPane jop = new JOptionPane();
		
		//lecture d’un fichier de données pour les auteurs
			if(a.getSource() == BaseAuteur){
				try {
					BdDonnees.lireBddAut("Auteurs.txt");
					jop.showMessageDialog(null,"vous venez de lire un "
								+ "fichier de données pour les auteurs", 
				        		"Informations", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
						e.printStackTrace();
				}
			}
		
		//lecture d’un fichier de données pour les livres
			if(a.getSource() == BaseLivre){
				try {
					try {
						BdDonnees.lireBddLivre("Livres.txt");
							jop.showMessageDialog(null,"vous venez de lire un "
									+ "fichier de données pour les livres", 
				        			"Informations", JOptionPane.INFORMATION_MESSAGE);
					}
					catch(java.lang.NullPointerException e){		
						jop.showMessageDialog(null,"vous devez lire le "
								+ "fichier de données pour les auteurs en premier", 
					       		 "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		
			//Quitter l'application
			if(a.getSource() == Quitter){
							System.exit(0);
						     }
			//A propos
			if(a.getSource() == apropos){
				jop.showMessageDialog(null, "Bibliographie vertuel\nVersion 1.0\nConcepteurs : Youcef Touat", 
					"A propos", JOptionPane.INFORMATION_MESSAGE);
				}

		//Recherche de la collection des oeuvres d'un auteur
		if(a.getSource() == colAuteur){
			Object[] options = { "Code Auteur", "Nom Auteur", "Annuler"};
			 int reponse = JOptionPane.showOptionDialog(null, 
					 "Choisissez votre mode de rechercher ", 
					  "Recherche Auteur" , JOptionPane.YES_NO_CANCEL_OPTION, 
					  JOptionPane.QUESTION_MESSAGE ,null, options, options[0]) ;
				if(reponse == JOptionPane.YES_OPTION ){
							int code =0;
							try{
							code = Integer.parseInt(jop.showInputDialog(null, "Entree le code de l'auteur", 
											"Auteur",JOptionPane.INFORMATION_MESSAGE));
							Auteur unAuteur = BdDonnees.getAuteur(code);
						if(BdDonnees.getColLivresAut(unAuteur)!=null){
							String Oeuvres = "";
						for(Livre ceLivre : BdDonnees.getColLivresAut(unAuteur)){
								Oeuvres += ceLivre.toString()+"\n";
								}
						jop.showMessageDialog(null, "Les oeuvres de "+unAuteur.getNom()+" :\n"+Oeuvres+"\n");
						}
						else
							jop.showMessageDialog(null, "Pas d'auteur avec le code "+ code +" dans notre base de données");
						}
						catch(NumberFormatException e){
							jop.showMessageDialog(null, "Vous devez entrer un entier pour le code");
						}
					}
			if(reponse == JOptionPane.NO_OPTION ){
						String nom = "";
						nom = jop.showInputDialog("Entree le nom de l'auteur");
						Auteur unAuteur = BdDonnees.getAuteur(nom);		
						if(BdDonnees.getColLivresAut(unAuteur)!=null){
						String Oeuvres = "";
						for(Livre ceLivre : BdDonnees.getColLivresAut(unAuteur)){
							Oeuvres += ceLivre.toString()+"\n";}
						jop.showMessageDialog(null, "Les oeuvres de "+nom+" :\n"+Oeuvres+"\n");
						}
						else
							jop.showMessageDialog(null, "Pas d'auteur avec le nom "+ nom +" dans notre base de données");
					  }
					}
		//Recherche d'un livre et afficher ces informations
		if(a.getSource() == livre){
					Object[] options = { "Code Livre", "Titre Livre", "Annuler"};
					int reponse = JOptionPane.showOptionDialog(null, "Choisissez votre mode de rechercher ", 
											  "Recherche Auteur" , JOptionPane.YES_NO_CANCEL_OPTION, 
						   					JOptionPane.QUESTION_MESSAGE ,null, options, options[0]) ;
				if(reponse == JOptionPane.YES_OPTION ){
					int code = Integer.parseInt(jop.showInputDialog(null, "Entree le code du livre", 
											      "Livre",JOptionPane.INFORMATION_MESSAGE));
					Livre unLivre = BdDonnees.getLivre(code);
						if(BdDonnees.getLivre(code).getCodeLivre()!=0){
							jop.showMessageDialog(null, "Les information de "+unLivre.getTitre()+" :\n"+unLivre+"\n");
						}
						else
							jop.showMessageDialog(null, "Pas de livre avec le code "+ code +" dans notre base de données");
						}
				if(reponse == JOptionPane.NO_OPTION ){
						String titre = jop.showInputDialog("Entree le titre du livre");
						Livre unLivre = BdDonnees.getLivre(titre);
						if(BdDonnees.getLivre(titre).getTitre()!= null)
						jop.showMessageDialog(null, "Les informations du livre du titre "+titre+"\n"+unLivre+"\n");
						else
							jop.showMessageDialog(null, "Pas de livre avec le titre "+ titre +" dans notre base de données");
					  }
					}
	//Ajouter un auteur a la base de donnees
		if(a.getSource() == addAuteur){
			//faire appelle a la fenetre creer pour la cueillette des informations de l'auteur a ajouter
			FenetrePersoAuteur sesieAuteur = new FenetrePersoAuteur();
			sesieAuteur = new FenetrePersoAuteur(null, "Informations auteur a ajouter", true);
			Auteur unAuteur = sesieAuteur.FenetreAuteur();
			BdDonnees.addAuteur(unAuteur);
		}
	//Ajouter un livre a la base de donnees
	if(a.getSource() == addLivre){
		//faire appelle a la fenetre creer pour la cueillette des informations du livre a ajouter
			FenetrePersoLivre sesieLivre = new FenetrePersoLivre();
			sesieLivre = new FenetrePersoLivre(null, "Informations auteur a ajouter", true);	
			Livre unLivre = sesieLivre.FenetreLivre();
			BdDonnees.addLivre(unLivre);
	}
	//generation du rapport sous forme d,une fenetre texte
	if(a.getSource() == rapAuteurLivre){
			try {
					BdDonnees.rapportParAuteurs();
					BdDonnees.rapportParLivres();
					JFrame frame = new FenetreRapport(400,350,"Rapport par Auteurs");
					frame.pack();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	//Aide
	if(a.getSource() == help){
		jop.showMessageDialog(null,"CTRL-A pour la lecture d’un fichier de données pour les auteurs"
			+ "\nCTRL-L pour la lecture d’un fichier de données pour les livres"
			+ "\nALT-A pour la recherche d’un auteur et l’affichage, à l’écran, de ses oeuvres"
			+ "\nALT-L  pour la recherche d’un livre et l’affichage à l’écran ses informations"
			+ "\nCTRL-Q pour fermer l'application"
			+ "\nF1 pour afficher les informations à propos"
			+ "\nF2 pour genérer un rapport"
			+ "\nt pour ajoutter un auteur a la base de données"
			+ "\nv pour ajoutter un livre a la base de données"
			+ "F4 pour obtenir de l'aide", 
			"Aide", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public static void main(String [] args) {
	     new FenetrePrincipale(500,300,"Bienvenue dans ma Bibliographie virtuelle");
	  }
}
