# DeSabre
Interface web pour des serveurs de calcul

## Installation

Faire un pull request / copier le zip. 
Importer dans Eclipse/IntelliJ en tant que projet Maven. 

Le lancement se fait par la classe Application.

Le serveur sera déployé sur localhost:8080
Les accès de test sont disponibles dans la classe security. 


## Composition 
3 modules 

Technologies utilisées : 
Web: 
ReactJS / Bootstrap / Thymeleaf

Server : 
Spring / MongoDB 

Niveau JDK : 8 



### Application 

L'application principale. Contient le code livrable. 

Le code est décomposé en partie web / serveur. 

Sous src/main/java se trovent le code serveur
Sous src/main/ressources/template  se trouvent les fragments html
Sous src/main/ressources/static/javascript/components se trouvent les widgets react


### database-init

Application pour initialiser la base de données avec les tests.

### database-model

Application containant les models et les repositories permettant d'y acceder.

