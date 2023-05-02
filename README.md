# ![](ressources/logo.jpeg)

# Développement Orienté Objets

### IUT Montpellier-Sète – Département Informatique


* [**Support de cours**](https://gitlabinfo.iutmontp.univ-montp2.fr/dev-objets/Ressources)
* **Enseignants:**
  [Marin Bougeret](mailto:marin.bougeret@umontpellier.fr),
  [Romain Lebreton](mailto:romain.lebreton@umontpellier.fr),<!--[Sophie Nabitz](mailto:sophie.nabitz@univ-avignon.fr),-->
  [Cyrille Nadal](mailto:cyrille.nadal@umontpellier.fr),
  [Victor Poupet](mailto:victor.poupet@umontpellier.fr),
  [Gilles Trombettoni](mailto:gilles.trombettoni@umontpellier.fr),
  [Petru Valicov](mailto:petru.valicov@umontpellier.fr)
* Le [forum Piazza](https://piazza.com/class/ld2tzi5k82via) de ce cours pour poser vos questions
* [Email](mailto:petru.valicov@umontpellier.fr) pour une question d'ordre privée concernant le cours.
* Le [sujet du TP](https://www.lirmm.fr/~pvalicov/Cours/dev-objets/TP9.pdf) en format .pdf téléchargeable et imprimable.

<!--Avant de démarrer le TP, vérifiez que vous n'avez pas atteint votre quota d'espace de stockage autorisé :

* placez-vous dans votre `$HOME` et utilisez les commandes suivantes :
    * `du -sh` pour voir combien d'espace vous avez déjà utilisé
    * `du -sh *` pour voir combien d'espace vous avez déjà utilisé pour chaque fichier (sans fichiers cachés)
    * `du -sch .[!.]* *` pour voir combien d'espace vous avez déjà utilisé pour chaque fichier, y compris les fichiers cachés
* Supprimez les fichiers inutiles.
* Pour éviter des problèmes durant vos TPs d'informatique, vous devriez toujours **garder 300-400 Mo d'espace libre**.
-->

### Consignes
- Vous respecterez les différents principes de programmation vus dans le cours et les TPs précédents
- Toutes vos classes doivent résider dans le paquetage `fr.umontpellier.iut`
- Les signatures des méthodes et les noms des classes qui vous sont données doivent rester inchangés.


## TP9 : _utilisation des collections Java_

Date limite de rendu de votre code sur le dépôt GitLab : **mercredi 19 avril à 23h00**

Reprenons la classe `Employe` du [TP5](https://gitlabinfo.iutmontp.univ-montp2.fr/dev-objets/TP5). Pour vous faciliter la tâche, nous l'avons directement écrite et simplifiée en enlevant les attributs inutiles pour ce TP. Un attribut `dateEmbauche` avec accesseur et modifieur ont été ajoutés à la classe `Employe`. Le but de ce TP est de proposer différentes organisations des employés dans l'entreprise en fonction des besoins du client. 

### Exercice 1 - mise en place

1. La classe `Entreprise` gère les employés à travers l'attribut `lePersonnel`- une [`Collection`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collection.html) (la structure de données itérable la plus générale possible). Ajoutez un constructeur sans paramètres instanciant cette collection en tant que `ArrayList`.

2. Complétez les méthodes `void embaucher(Employe e, LocalDate dateEmbauche)` et `void licencier(Employe e)` de la classe `Entreprise` afin de mettre à jour la liste d'employés de manière correspondante. Lors du licenciement de l'employé, vous mettrez à `null` sa date d'embauche.

   **Remarque importante** : Pour simplifier, vous supposerez qu'une même référence `Employe` ne sera passée qu'une seule fois en paramètres à la méthode `embaucher(Employe e, LocalDate dateEmbauche)`. Donc, vous n'avez pas à gérer le cas où l'utilisateur de votre application embauche le **même objet** `Employe` dans la même `Entreprise`.

3. Redéfinissez la méthode `String toString()` de la classe `Entreprise` pour afficher ses informations.

4. Vérifiez votre programme en créant dans la classe principale (`GestionEmployes`) une entreprise et en embauchant plusieurs employés. Vous afficherez l'état de l'entreprise après chacune des opérations.

### Exercice 2 - organisation des employés

L'entreprise souhaite mieux organiser ses employés.

1. Redéfinissez les méthodes `equals(Object o)` et `hashCode()` de la classe `Employe` afin de distinguer deux employés en fonction de leur numéro INSEE et leur nom (de type `String`). Dorénavant deux employés seront considérés comme des doublons s'ils ont le même numéro INSEE et le même nom.

   **Remarque importante** : 
   votre méthode `licencier(Employe e)` supprime la première occurrence de l'employé `e` *au sens de `equals`* (même numéro INSEE et même nom).
   Or, la collection `lePersonnel` peut contenir le même employé (au sens de `equals`) plusieurs fois si cette personne occupe des postes différents.
   Dans ce cas, votre méthode `licencier(Employe e)` va maintenant licencier l'employé de l'un de ses postes, mais pas de l'autre, sans qu'on puisse contrôler quel poste est visé.  
   En conclusion, la méthode `licencier` n'est pas faite pour traiter le cas où un employé a été embauché à plusieurs postes. **Ne changez pas** la méthode `licencier` pour autant.
   
   <!-- Donc ici, vous ne devez pas toucher au code de l'attribut `lePersonnel` de la classe `Entreprise`. --> 

1. Écrivez le corps de la méthode `Collection<Employe> getEmployesDansDesordre()`. À partir de la collection `lePersonnel`, elle devra retourner une autre collection en enlevant tous les doublons, et ce, **sans invoquer explicitement un algorithme de recherche de doublons**.

   **Attention** : Pas de modifications du code précédemment écrit (et donc de l'attribut `lePersonnel`).

1. Maintenant, pour une meilleure lisibilité, l'entreprise souhaite pouvoir retrouver l'ensemble de ses employés sans les doublons, mais dans l'ordre. L'ordre choisi est l'ordre _croissant_ suivant le nom et qui en cas d'égalité, applique l'ordre _décroissant_ suivant le numéro INSEE.
    
    Écrivez le corps de la méthode `Collection<Employe> getEmployesOrdonnes()` qui, à partir de la collection `lePersonnel`, retourne une autre collection respectant ces contraintes. Naturellement, comme dans la question précédente, **il ne faut pas écrire ou invoquer explicitement un algorithme de recherche de doublons, ni un algorithme de tri**.

   **Remarque** : les deux fonctions  `getEmployesDansDesordre()` et `getEmployesOrdonnes()` doivent être totalement indépendantes et ne doivent pas s'appeler entre elles.

1. Écrivez plusieurs tests unitaires vérifiant la fonctionnalité programmée. Voici le scénario à appliquer dans chaque test :
    * créer une entreprise
    * créer plusieurs employés (au moins 4) avec des noms différents et/ou numéros INSEE différents
    * vérifiez avec des _assertions_ (`assertEquals(...)`, `assertNotEquals(...)`, `assertTrue(...)`, `assertFalse(...)`, `assertIterableEquals(...)` etc.) que la méthode `getEmployesOrdonnes()` fonctionne correctement. Vous vérifierez notamment que les collections retournées par `getEmployesOrdonnes()` et `getEmployesDansDesordre()` sont de mêmes tailles (et contiennent les mêmes employés). Vous trouverez la liste exhaustive des assertions en _Junit 5_ dans [l'API de la classe `Assertions`](https://junit.org/junit5/docs/5.4.0/api/org/junit/jupiter/api/Assertions.html).
   
   <!-- [un tutoriel en ligne](https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-assertions-with-junit-5-api/) -->
        


### Exercice 3 - priorité aux anciens

L'entreprise souhaite distribuer des bonus à ses employés en fonction de la date d'embauche. Le problème est que cette somme est évidemment limitée, donc on risque de ne pas pouvoir distribuer des bonus à chaque employé... Cette somme est représentée par l'attribut `double bonusTotal` de la classe `Entreprise`. Un _setter_ permet à l'utilisateur de fixer à tout moment la somme d'argent disponible pour distribuer un bonus aux employés.

L'attribut `double bonus` de la classe `Employe` permet de définir la quantité de bonus qu'un employé va recevoir. Les méthodes _setter_ et _getter_ permettent la gestion de ce bonus.

1. Dans la classe `Employe` la méthode `int getMoisAnciennete()` renvoie le nombre de mois correspondant à l'intervalle de temps entre la date d'embauche et maintenant. Le corps de cette méthode vous est donné et vous ne devez pas le modifier. L'ancienneté est calculée sur le nombre de mois **complets** depuis la date d'embauche à l'aide du type énuméré [`ChronoUnit`](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/ChronoUnit.html). Cette classe permet d'effectuer des calculs en fonction de différentes unités temporelles (jours, mois, années, etc.).

   Écrivez plusieurs tests unitaires afin de comprendre le fonctionnement de la méthode `int getMoisAnciennete()` de la classe `Employe`. Vérifiez notamment que deux personnes étant embauchées à des dates différentes, mais ayant effectué le même nombre de mois **complets** aient la même ancienneté. Par exemple, un employé embauché depuis 10 mois et 14 jours aura la même ancienneté qu'un employé embauché depuis 10 mois. Prêtez attention à la variation des longueurs des mois dans une année !

1. Le patron a décidé de donner la priorité aux anciens pour la distribution du bonus. Ainsi, le bonus sera distribué aux employés suivant leurs dates d'embauche : de la plus ancienne, à la plus récente. Chaque employé va voir son bonus incrémenté de `3.5*ancienneté`.

   Écrivez le corps de la méthode `void distribuerBonus()` qui effectue cette tâche **sans utiliser explicitement** un algorithme de tri et **sans modifier la classe `Employe`** Écrivez des tests unitaires pour vous assurer que chaque employé a bien reçu le bon bonus.

   **Remarques :**
   * Pour déterminer l'ordre de distribution du bonus de deux employés embauchés à des dates identiques, vous prendrez l'ordre d'apparition dans la collection `lePersonnel`.
   * Un employé embauché à plusieurs postes (qui apparaît plusieurs fois dans `lePersonnel`), percevra plusieurs fois le bonus.
   * Le bonus étant limité, il se peut que certains employés ne touchent rien (notamment les plus jeunes). De même, si vers la fin de la distribution, la quantité de bonus restante est inférieure à `3.5*ancienneté`, alors l'employé recevra seulement la quantité de bonus restante et tant pis pour son ancienneté !

1. Modifiez la méthode `toString()` de `Employe` afin qu'elle affiche également le bonus que l'employé a reçu.

1. L'entreprise traverse une période de crise et décide de se séparer d'une partie de ses employés. Afin de fidéliser les anciens employés, ce qui a été décidé, c'est de licencier les employés ayant travaillé le moins longtemps dans l'entreprise. Sans modifier le code précédemment écrit, écrivez le code de la méthode `void remercier(int n)` de la classe `Entreprise` pour licencier `n` employés ayant été embauchés le plus tard.

   **Remarques importantes :** Comme dans le cas de la question 2, si deux employés sont embauchés à des dates identiques, vous les remercierez dans l'ordre d'apparition dans la collection `lePersonnel`. Également, un employé peut être licencié d'un poste, mais pas d'un autre.

   **Astuce** : Pour cette question, pensez à vérifier le scénario suivant :
   1. Créer 3 employés comme ceci
      * 2 employés _fifi_ et _loulou_ avec le même numéro INSEE et le même nom et des bases différentes
      * 1 employé _toto_ avec numéro INSEE, nom et base quelconques
   2. Embaucher d'abord _toto_ avec une date d'embauche la plus ancienne (disons 1er janvier 2000), ensuite _fifi_ (18 mars 2023) et ensuite _loulou_ (30 mars 2023)
   3. Remercier qu'un seul employé en invoquant `remercier(1)` et vérifier que tout fonctionne correctement.
   

### Exercice 4 - indemnités de transport

On souhaite maintenant pouvoir calculer les indemnités de transport pour chaque employé en fonction de la distance entre sa ville de résidence (une donnée de type `String`) et les locaux de l'entreprise.
L'attribut `String adresse` de la classe `Employe` correspond à sa ville de résidence. Le _getter_ et le _setter_ permettent la gestion de cet attribut.

La classe `GestionDistances` gère une collection statique faisant correspondre une distance (un entier) à une ville. Il s'agit d'une `Map`. Une ville ne peut être associée qu'à une unique distance, mais une même distance peut être associée à plusieurs villes. La `Map` est initialisée avec les données suivantes :

* Montpellier &rightarrow; 0
* Sète &rightarrow; 36
* Sommières &rightarrow; 30
* Nîmes &rightarrow; 58
* Lunel &rightarrow; 30
* Béziers &rightarrow; 80

**Attention :** la collection `distances` est statique, donc n'ajoutez pas de constructeur à la classe `GestionDistances`...

1. Déclarez une classe d'exception contrôlée `AdresseInconnueException` héritant de `Exception`. Le constructeur de cette classe aura comme argument un objet `String nomVille` et appellera le constructeur de la classe de base (`Exception`) avec le message : "_La ville " + `nomVille` + ” n'existe pas_”

1. La méthode `static int getDistance(String ville)` de `GestionDistances` devra retourner la distance associée à la ville passée en paramètre. Modifiez la signature de cette fonction annonçant qu'elle est **susceptible** de lever une exception `AdresseInconnueException`. Écrivez ensuite le corps de `static int getDistance(String ville)` en veillant à ce qu'elle **lève** une exception `AdresseInconnueException` si la ville n'est pas présente dans la collection `distances`.

1. Écrivez le corps de la méthode `double getIndemniteTransport()` de la classe `Employe`. Elle doit retourner l'indemnité qui est due à l'employé. La formule de calcul de cette indemnité est `distance  * base`. Si la ville n'existe pas, cette méthode devra traiter l'exception correspondante et retourner `0`.

1. Écrivez des tests unitaires pour vérifier le bon fonctionnement de la méthode `double getIndemniteTransport()`.
