 /**************************
 *   Nom: Bdd.java         *
 *   Auteur: Youcef Touat  *
 **************************/
import java.io.*;
import java.util.*;

	public class Bdd 
	{
		private Map<Auteur, Set<Livre>> MapAuteur;
		
		//constructeur par defaut
		public Bdd(){
		//initialisation du LinkedHashMap 
		MapAuteur = new LinkedHashMap<Auteur, Set<Livre>>(); //vide
	}

/***************************************************************
 * Methode pour lire le fichier d’auteurs passé en argument,   *
 * et ajouter les auteurs au map.                              *
 **************************************************************/
	public void lireBddAut(String nomFichier)
				throws IOException
	{
		boolean existeFichier = true ; 
		FileReader fr = null;
		//LOCALISER le fichier à partir de son nom
		try {
			fr = new FileReader (nomFichier) ;
		}
		// intercepter l'erreur si le fichier n'existe pas
		catch ( java.io.FileNotFoundException erreur) {
		System.out.println("Probleme d'ouverture du fichier " + nomFichier);
		existeFichier = false ; 
		}
		if (existeFichier) {
		BufferedReader entree = new BufferedReader(fr);
		boolean finFichier = false ;
		while ( !finFichier ) {
		// lire une ligne
		String uneLigne = entree.readLine();
		if (uneLigne == null)
			finFichier = true ;
		else {
			StringTokenizer st = new StringTokenizer(uneLigne, "\t");
			String codeStr = st.nextToken();
			int code = Integer.parseInt(codeStr.trim());
			String nom = st.nextToken();
			String paysOrigine = st.nextToken();
			MapAuteur.put(new Auteur(nom, code, paysOrigine), new TreeSet<Livre>());
			}
		}
		entree.close(); //fermeture du fichier
		}
	}

/****************************************************************************
 * Methode pour ajoute un auteur passé en argument, dans le map.            * 
 * La partie valeur associée a cet auteur est une nouvelle collection vide. *  
 ****************************************************************************/
	public void addAuteur(Auteur unAuteur)
	{
		if(MapAuteur.containsKey(unAuteur))
			System.out.println("Le code auteur "+unAuteur.getCode()+" existe déjà!!");
		else
			MapAuteur.put(unAuteur, new TreeSet<Livre>());
	}
	
/*******************************************************************
 * Methode pour lire les livres dans un fichier passé en argument  * 
 * et d'ajouter chaque livre a la collection associé à son auteur. * 
 ******************************************************************/
	public void lireBddLivre(String nomFichier)
			throws IOException
	{
	boolean existeFichier = true ; 
	FileReader fr = null;
	//LOCALISER le fichier à partir de son nom
	try {
	fr = new FileReader (nomFichier) ;
	}
	// intercepter l'erreur si le fichier n'existe pas
	catch ( java.io.FileNotFoundException erreur) {
	System.out.println("Probleme d'ouverture du fichier " + nomFichier);
	existeFichier = false ; 
	}
	if (existeFichier) {
	BufferedReader entree = new BufferedReader(fr);
	boolean finFichier = false ;
	while ( !finFichier ) {
	// lire une ligne
	String uneLigne = entree.readLine();
	if (uneLigne == null)
		finFichier = true ;
	else {
		StringTokenizer st = new StringTokenizer(uneLigne, "\t");
		int codeLivre = Integer.parseInt(st.nextToken().trim());
		String titre = st.nextToken();
		String categorie = st.nextToken();
		int codeAuteur = Integer.parseInt(st.nextToken().trim());
		double prix = Double.parseDouble(st.nextToken().trim());
		int nbrPages = Integer.parseInt(st.nextToken().trim());
		Livre unLivre = new Livre(titre, codeLivre, codeAuteur, categorie, nbrPages, prix);
		//Creer un auteur associé au livre lu avec son codeAuteur
		Auteur unAuteur = new Auteur(codeAuteur);
		//Trouver et ajouter ce livre a la collection de cet auteur
		MapAuteur.get(unAuteur).add(unLivre);
		}	
	}
		entree.close(); // fermer le fichier apres la lecture
		}
	}
	
/*************************************************************
 * Methode pour ajouter un livres a la base de donnees et de *
 * l'associer a la collection correspondante a son auteur    *
 *************************************************************/
	public void addLivre(Livre unLivre)
	{
		//Creer un auteur associé au livre lu avec son codeAuteur
		Auteur auteurLivre = new Auteur(unLivre.getCodeAuteur());
		//Voir si le map contiens l'auteur du livre 
		if(MapAuteur.containsKey(auteurLivre)){
			//Trouver et ajouter ce livre a la collection de cet auteur
			MapAuteur.get(auteurLivre).add(unLivre);
		}
		else
			System.out.println("L'auteur du livre "+ unLivre.getTitre()+
									" n'est pas dans la base de données");
	}

/*****************************************************************************
 * Methode pour chercher un auteur present dans la map en utilisant son nom  *  
 *****************************************************************************/
	public Auteur getAuteur(String nom){
		Auteur auteurAchercher = new Auteur();
		for (Map.Entry<Auteur, Set<Livre>> entry : MapAuteur.entrySet()){
			if(entry.getKey().getNom().equalsIgnoreCase(nom))
				auteurAchercher = entry.getKey();
		}
		return  auteurAchercher;
	}
	
/*****************************************************************************
 * Methode pour chercher un auteur present dans la map en utilisant son code.*  
 ****************************************************************************/
	public Auteur getAuteur(int codeAuteur){
		Auteur auteurAchercher = new Auteur();
		Auteur unAuteur = new Auteur(codeAuteur);
		for (Iterator<Auteur> itClef = MapAuteur.keySet().iterator(); itClef.hasNext();){
			Auteur clef = itClef.next();
			if(clef.equals(unAuteur))
				auteurAchercher = clef;
			}
		return  auteurAchercher;
	}
	
/*******************************************************************
 * Methode pour chercher un Livre d'un auteur present dans la map  * 
 * en utilisant le titre de ce livre                               *
 ******************************************************************/
	public Livre getLivre(String titre){
		Livre unLivre = new Livre(titre);
		Livre livreAchercher = new Livre();
		Set<Livre> OeuvresAuteur = new TreeSet<Livre>();
		for (Iterator<Map.Entry<Auteur, Set<Livre>>> entry = 
					MapAuteur.entrySet().iterator();entry.hasNext();){
			Map.Entry<Auteur, Set<Livre>> a = entry.next();
				OeuvresAuteur = a.getValue();
		for(Livre ceLivre : OeuvresAuteur){
			if(ceLivre.equals(unLivre))
				livreAchercher = ceLivre;
			}
		}	
		return livreAchercher;
	}
	
/******************************************************************
 * Methode pour chercher un Livre d'un auteur present dans la map *
 * en utilisant le code de l'auteur de ce livre                   *
 *****************************************************************/
	public Livre getLivre(int codeLivre){
		Livre livreAchercher = new Livre();
		Set<Livre> OeuvresAuteur = new TreeSet<Livre>();
		for (Iterator<Map.Entry<Auteur, Set<Livre>>> entry = 
					MapAuteur.entrySet().iterator();entry.hasNext();){
			Map.Entry<Auteur, Set<Livre>> a = entry.next();
				OeuvresAuteur = a.getValue();
				for(Livre ceLivre : OeuvresAuteur){
					if(ceLivre.getCodeLivre()==codeLivre)
						livreAchercher = ceLivre;
				}
			}
			return livreAchercher;
	}
	
/**********************************************************************
 * Methode pour chercher la collection de livres associer a un auteur * 
 * present dans la map en passant cet auteur en paramètre             *
 **********************************************************************/
	public Collection<Livre> getColLivresAut(Auteur unAuteur){
		return MapAuteur.get(unAuteur);
	}
	
/*****************************************************************************
 * Methode pour crée un fichier parAuteur.txt contenant la liste des auteurs *
 * et de leurs livres par ordre alphabétique des auteurs puis des livres.    *
 ****************************************************************************/
	public void rapportParAuteurs() throws IOException
	{
		boolean probleme = false;
		FileWriter fw = null;
		String nomACreer = "parAuteur.txt";
		Map<Auteur, Set<Livre>> MapTrier = new LinkedHashMap<Auteur, Set<Livre>>();
		//Trier le map par ordre alphabétique des noms d'auteurs.
		MapTrier = trierMap(MapAuteur);
		try{
			fw = new FileWriter(nomACreer);			
		} 
		catch (java.io.FileNotFoundException erreur){
			System.out.println("Problème de préparation d'écriture\n");
			probleme = true;
		}
		if (!probleme){
			PrintWriter aCreer = new PrintWriter( fw );
			for (Map.Entry<Auteur, Set<Livre>> entry : MapTrier.entrySet()){
				aCreer.println(entry.getKey().getNom()+" :\n");
				if(entry.getValue().isEmpty())
					aCreer.println("\t\nCet auteur n’a pas de livres répertoriés.");
				else{
					Iterator<Livre> itValeur = entry.getValue().iterator();
					while(itValeur.hasNext()){
						aCreer.println("\t\n" + itValeur.next());
					}
				}
			}	
			aCreer.close();
			System.out.println("Le fichier " + nomACreer+ " a été creé");				
		}
	}
	
/****************************************************************************
 * Methode pour trier un LinkedHashMap par ordre alphabétique des auteurs   *
 * tout en gardant chaque collection de livres avec son auteur.             *
 ****************************************************************************/
	public Map<Auteur, Set<Livre>> trierMap(Map<Auteur, Set<Livre>> mapAtrier){	
		List<Map.Entry<Auteur, Set<Livre>>> ListAuteur = 
					new ArrayList<Map.Entry<Auteur, Set<Livre>>>(mapAtrier.entrySet());
			Collections.sort(ListAuteur, new Comparator<Map.Entry<Auteur, Set<Livre>>>(){
			//implementation de l'interface comparator //just pour cette fonction
			public int compare(Map.Entry<Auteur, Set<Livre>> a, 
												Map.Entry<Auteur, Set<Livre>> b){
			return (a.getKey()).compareTo(b.getKey());
			}
		});
		Map<Auteur, Set<Livre>> MapAuteurTrier = new LinkedHashMap<Auteur, Set<Livre>>();
		for (Map.Entry<Auteur, Set<Livre>> entry : ListAuteur) {
			MapAuteurTrier.put(entry.getKey(), entry.getValue());
		}
		return MapAuteurTrier;
	}
	
/****************************************************************************
 * Methode pour Crée un fichier parLivre.txt contenant la liste des livres  *
 * et des auteurs par ordre alphabétique des titres de livre.               *
 ****************************************************************************/
	public void rapportParLivres() throws IOException
	{
		boolean probleme = false;
		FileWriter fw = null;
		String nomACreer = "parLivre.txt";
		try{
			fw = new FileWriter(nomACreer);			
		} 
		catch (java.io.FileNotFoundException erreur){
			System.out.println("Problème de préparation d'écriture\n");
		probleme = true;
		}
		if (!probleme){
			PrintWriter aCreer = new PrintWriter( fw );
			Set<Livre> toutesLesOeuvrs = new TreeSet<Livre>();
		for (Map.Entry<Auteur, Set<Livre>> entry : MapAuteur.entrySet()){
			Iterator<Livre> itValeur = entry.getValue().iterator();
			while(itValeur.hasNext()){
				toutesLesOeuvrs.add(itValeur.next());
			}
		}
		for(Livre ceLivre : toutesLesOeuvrs){
			aCreer.print(ceLivre);
			for(Auteur clef : MapAuteur.keySet()){
				if(clef.getCode()== ceLivre.getCodeAuteur())
				aCreer.println(clef.getNom());
			}
		}
		aCreer.close();
			System.out.println("Le fichier " + nomACreer+ " a été creé");				
		}
	}
	
	//Redefinition de la methode toString pour la classe Bdd 
	//pour afficher en ordre alphabétique des noms d'auteurs.
	
		
	public String toString(){
		Map<Auteur, Set<Livre>> MapTrier = new LinkedHashMap<Auteur, Set<Livre>>();
		//Trier le map par ordre alphabétique des noms d'auteurs.
		MapTrier = trierMap(MapAuteur);
		String resultat ="";
		for (Map.Entry<Auteur, Set<Livre>> entry : MapTrier.entrySet()){
			resultat += "\n"+entry.getKey().getNom()+" :\n";
			if(entry.getValue().isEmpty())
				resultat +=	"Cet auteur n’a pas de livres répertoriés.";
			else{
				Iterator<Livre> itValeur = entry.getValue().iterator();
					while(itValeur.hasNext()){
						resultat += "\t"+itValeur.next()+"\n";
					}
				}
		}
		return resultat;
	}
}