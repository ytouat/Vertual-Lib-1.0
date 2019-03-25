/*****************************
 *	Nom: Auteur.jav      *
 *	Auteur: Youcef Touat *
 *****************************/

	public class Auteur 
		implements Comparable<Auteur>
	{
		private int code;
		private String nom;
		private String pays;
	
		//Constructeur par defaut (sans parametres)
		public Auteur()
		{
		}
		//Constructeur en passant seulement le code en parametre
		public Auteur(int code)
		{
			this.code = code;
		}
	
		//Constructeur en passant tous les atribus d'un auteur en parametres
		public Auteur(String nom, int code, String pays)
		{
			this.nom = nom;
			this.code = code;
			this.pays = pays;
		}
		
		//Getter et Setter
		public int getCode()
		{
			return code;	
		}
	
		public void setCode(int nouvCode)
		{
			code = nouvCode;
		}
	
		public String getNom()
		{
			return nom;	
		}
		public String getPays()
		{
			return pays;	
		}
	
		public void setNom(String nouvNom)
		{
			nom = nouvNom;
		}
	
	//Redefinitin de la methode toString	
		public String toString()
		{
			return String.format(" %-10d %-30s %-10s",code, nom, pays);
		}

	//Redefinition de la methode equals
		public boolean equals(Object obj)
		{
			 if (! (obj instanceof Auteur))
				return false;
	 		else
				if (this == obj)
					return true;
				else{
					Auteur autre = (Auteur) obj;
					return code == autre.code;
			     	}
	 	}
	//Redefinition de la methode hashCode
		public int hashCode() 
		{
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
		}
	
	//implementation de l'interface comparable
		public int compareTo(Auteur autre) 
		{
		return nom.compareTo(autre.nom);
		}	
	}