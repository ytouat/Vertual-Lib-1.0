# Vertual-Lib-1.0

Le fichier Auteurs.txt contient un ensemble de données sur les auteurs.
Le fichier Livres.txt contient un ensemble de données sur les livres.

Cette partie représente la création d’une interface graphique fonctionnel de
gestion d’une banque de données d’œuvres (Livres) et d’auteurs.

Voila une brève description des fonctions implémentées dans la classe principale Bdd.

•	lireBddAut: permet de lire un fichier d’auteurs passé en argument de créer la map et ajouter les auteurs à la map. La partie valeur associée à chaque auteur est une nouvelle collection vide.
•	addAuteur: ajoute un auteur, passé en argument, dans la map.  La partie valeur associée est une nouvelle collection vide. Si l’auteur s’y trouve déjà, l’ajout est ignoré (pas de remplacement).
•	lireBddLivre: permet de lire les livres à partir d'un fichier passé en argument. Chaque livre est ajouté à la collection associé à son auteur. Un livre dont l’auteur n’est pas déjà dans la map est ignoré silencieusement.
•	addLivre: ajoute un objet livre, passé en argument, à la collection de l’auteur dont le code est aussi passé en argument.Si l’auteur n’est pas déjà dans la map, l’ajout du livre est ignoré.
•	getAuteur: Retourne l’objet Auteur, se trouvant dans la map associé à un auteur dont l’Auteur (une référence sur un objet considéré égal) est passé en paramètre.
•	getAuteur: Surdéfinition de la précédente mais cette fois, c’est le code numérique de l’auteur qui est passé en paramètre.
•	getLivre: Retourne l’objet livre, dont le titre est passé en argument.
•	getLivre: Surdéfinition de la précédente mais cette fois, c’est le code numérique du livre qui est passé en paramètre.
•	getColLivresAut : Reçoit en paramètre un auteur et retourne sa collection de livre (valeur associée dans la Map)
•	rapportParAuteurs : Crée un fichier parAuteur.txt contenant la liste des auteurs et de leurs livres par ordre alphabétique des auteurs puis des livres.
•	rapportParLivres : Crée un fichier parLivre.txt contenant la liste des livres et des auteurs par ordre alphabétique des titres de livre.
•	toString : Pour permettre l’affichage de la map en ordre croissant du nom des auteurs.


