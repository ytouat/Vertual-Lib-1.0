/*******************************
 *	Nom: Livre.java        *
 *	Auteur: Youcef Touat   *
 *******************************/

public class Livre 
		implements Comparable<Livre>
{
	private int codeLivre, codeAuteur, nbrPages;
	private String titre;
	private String categorie;
	private double prix;
	
	//Constructeur par defaut (sans parametres)
	public Livre()
	{
	}
	//Constructeur en passant seulement le titre en parametre
	public Livre(String titre)
	{
		this.titre = titre;
	}
	//Constructeur en passant tous les atribus d'un livre en parametres
	public Livre(String titre, int codeLivre, int codeAuteur, 
            	 				String categorie, int nbrPages, double prix)
	{
		this.codeLivre = codeLivre;
		this.titre = titre;
		this.categorie = categorie;
		this.codeAuteur = codeAuteur;
		this.prix = prix;
		this.nbrPages = nbrPages;
	}
	
	//Getter et Setter
	public int getCodeLivre()
	{
		return codeLivre;
	}
	
	public void setCodeLivre( int nouvCodeLivre)
	{
		nouvCodeLivre = codeLivre;
	}
	
	public int getCodeAuteur()
	{
		return codeAuteur;
	}
	
	public void setCodeAuteur( int nouvCodeAuteur)
	{
		nouvCodeAuteur = codeAuteur;
	}
	
	public String getTitre()
	{
		return titre;
	}
	
	public void setTitre( String nouvTitre)
	{
		nouvTitre = titre;
	}
	
	//implementation de l'interface comparable
	public int compareTo(Livre autre)
	{
	    return titre.compareTo(autre.titre);
	}
	
	//Redefinitin de la methode toString	
	public String toString()
	{
		return String.format("%-40s %-10s %10.2f$ %-10d", titre, categorie, prix, nbrPages);
	}
		
	//Redefinition de la methode equals
	public boolean equals(Object obj)
	{
		if (! (obj instanceof Livre))
			return false;
		 else
			if (this == obj)
				return true;
			else{
				Livre autre = (Livre) obj;
				return titre.equalsIgnoreCase(autre.titre);
			}
	}
}
