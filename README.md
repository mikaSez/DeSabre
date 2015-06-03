# DeSabre
Interface web pour des serveurs de calcul

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

