# Devops Project
---
![Github CI Bagde](https://github.com/atlantic16/devops_project/actions/workflows/maven.yml/badge.svg)

## Fonctionnalités Principales:
Les fonctionnalités principales de notre bibliothèque sont les suivants: 

1. Création d'un Dataframe à partir d'une liste d'elements, ou à partir d'un fichier du type CSV suivant le format : 
`Label,Type,value1,value2...`. On s'assure de la cohérence sur le type donnée stocké dans chaque colonne. 
2. Affichage d'un Dataframe avec trois fonctions d'affichage differentes:
    * Afficher tout le dataframe avec `show()`
    * Afficher n première lignes du dataframe à l'aide de la fonction `head(int n)`
    * Afficher n dernière lignes du dataframe à l'aide de la fonction `tail(int n)`
3. Sélection d'un dataframe avec deux modes differents: par colonnes ou par lignes.
4. Statistiques sur un dataframe:
    * Calcul du maximum d'une colonne
    * Calcul du minimun d'une colonne
    * Calcul de la moyenne d'une colonne

## Choix d’outils:

- GitHub
- Git
- Maven
- Jacoco (Code coverage)
- Github CI/CD Actions
- Docker

## Workflow Git et procédure de Pull/Merge requests:

Le workflow adapté pour ce projet utilise Git. Nous avons trois branches principales et nous faisons une revue de code à chaque fois qu'il y a des changements à fusionner dans la branche master :

- **master**  : Cette branche représente la version principale de notre projet. C'est là que nous fusionnons les versions compilées et testées de nos branches de développement.

- **feature-DataFrame** : Cette branche est dédiée au développement de fonctionnalités spécifiques, en l'occurrence celles liées au DataFrame dans notre projet. Les nouvelles fonctionnalités sont développées et testées dans cette branche avant d'être fusionnées dans la branche principale (master).

- **testing** : Cette branche est utilisée pour développer des tests supplémentaires et garantir la qualité du code avant de fusionner les modifications dans la branche principale (master).

Nous avons configuré deux pipelines distinctes utilisant GitHub CI/CD Actions pour garantir le bon fonctionnement de notre code :

1. Pipeline pour la branche master (maven.yaml) : Activée à chaque push ou pull request sur la branche master pour garantir la stabilité du code avant la fusion.
2. Pipeline pour la branche feature-DataFrame (feature.yaml): Déclenchée à chaque push sur la branche feature-DataFrame pour valider spécifiquement les modifications faites et les testés.

## Docker [OPTIONAL]:
