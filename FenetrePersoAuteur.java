/***************************************
 *	Nom: FenetrePersoAuteur.java   *
 *	Auteur: Youcef Touat           *
 **************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class FenetrePersoAuteur extends JDialog {
		private Auteur unAuteur = new Auteur();
	  	private JLabel nomLabel, paysLabel, codeLabel;
	  	private JTextField nom, pays, code;
	  	
	public FenetrePersoAuteur(){
	  	}
	public FenetrePersoAuteur(JFrame parent, String title, boolean modal){
	    	super(parent, title, modal);
	    	this.setSize(300, 200);
	    	this.setLocationRelativeTo(null);
	    	this.setResizable(false);
	    	this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    	this.initComponent();
	}
	  
	public Auteur FenetreAuteur(){
		this.setVisible(true);      
	    	return this.unAuteur;      
	}

	
	private void initComponent(){

	    //Le nom
	    JPanel panNom = new JPanel();
	    panNom.setBackground(Color.white);
	    nom = new JTextField();
	    nom.setPreferredSize(new Dimension(150, 25));
	    nomLabel = new JLabel("Nom :");
	    panNom.add(nomLabel);
	    panNom.add(nom);

	    //Le code
	    JPanel panCode = new JPanel();
	    panCode.setBackground(Color.white);
	    code = new JTextField();
	    code.setPreferredSize(new Dimension(150, 25));
	    codeLabel = new JLabel("Code :");
	    panCode.add(codeLabel);
	    panCode.add(code);
	   
	    //le pays
	    JPanel panPays = new JPanel();
	    panPays.setBackground(Color.white);
	    pays = new JTextField();
	    pays.setPreferredSize(new Dimension(150, 25));
	    paysLabel = new JLabel("Pays :");
	    panPays.add(paysLabel);
	    panPays.add(pays);
	    JPanel content = new JPanel();
	    content.setBackground(Color.white);
	    content.add(panNom);
	    content.add(panCode);
	    content.add(panPays);
	    JPanel control = new JPanel();
	    final JButton okBouton = new JButton("OK");
	    
	    okBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e) {
	    	  JOptionPane jop = new JOptionPane();
	    	  try { 
	    		  unAuteur = new Auteur(nom.getText(), Integer.parseInt(code.getText()), pays.getText());
	    		  setVisible(false);
	    		  } catch (NumberFormatException ex) {  
	    		     jop.showMessageDialog(null, " Entrez toutes les informations", "Erreur", JOptionPane.ERROR_MESSAGE);
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