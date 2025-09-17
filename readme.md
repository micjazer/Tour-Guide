# TourGuide Application Projet 8 OpenClassrooms

## Aperçu

**TourGuide** est une application Java conçue pour fournir des recommandations personnalisées aux touristes en fonction de leur emplacement. Elle intègre plusieurs services externes pour le suivi des emplacements, le calcul des récompenses et la tarification des voyages, dans le but d'offrir une expérience riche et interactive.

---

## Key Features

1. **User Location Tracking**  
   Suivi de l'emplacement en temps réel des utilisateurs pour une meilleure personnalisation.

2. **Nearby Attractions**  
   Identification des attractions à proximité immédiate de l'utilisateur.

3. **Reward System**  
   Calcul et suivi des points de récompense lorsque les utilisateurs visitent des attractions.

4. **Personalized Trip Deals**  
   Génération de propositions de voyages adaptées aux préférences des utilisateurs.

5. **Performance Optimization**  
   Gestion efficace d'un grand nombre d'utilisateurs grâce à la prise en charge du multi-threading.

---

## Structure du projet

```plaintext
TourGuide/
├── src/
│   ├── main/java/tourGuide/
│   │   ├── controller/        # Contrôleurs REST API
│   │   ├── helper/            # Classes utilitaires
│   │   ├── service/           # Services métier et logique
│   │   ├── model/             # Modèles de données
│   │   ├── user/              # Gestion des utilisateurs
│   │   ├── tracker/           # Gestion de la localisation
│   │   └── TourGuideApplication.java  # Point d'entrée de l’application
│   └── test/                  # Classes de tests unitaires et d'intégration
├── libs/                      # Bibliothèques JAR externes
├── Dockerfile                 # Configuration de conteneur Docker
├── .github/workflows/         # Fichiers de pipeline CI/CD GitHub Actions
├── pom.xml                    # Gestion des dépendances Maven
└── README.md                  # Documentation du projet
```

---

## Technical Stack

- **Java 17** : Langage de programmation principal
- **Spring Boot 3.1.15** : Framework pour la création d'applications Java
- **Maven** : Outil de gestion des dépendances et de construction
- **JUnit & Mockito** : Frameworks de tests unitaires
- **JaCoCo** : Outil de mesure de la couverture des tests
- **Docker** : Conteneurisation pour déploiement
- **GitHub Actions** : Pipelines CI/CD

---

## Dépendances externes

L'application utilise trois bibliothèques JAR externes situées dans le répertoire `libs/` :

1. **gpsUtil.jar** : Fournit des services de localisation GPS.
2. **RewardCentral.jar** : Gère le calcul des récompenses utilisateur.
3. **TripPricer.jar** : Fournit les prix des voyages personnalisés.

---

## CI/CD Pipeline

L'intégration continue et le déploiement sont configurés sur **GitHub Actions**, avec deux workflows principaux :

1. **Workflow : Génération d'artefacts**
    - Compilation de l'application.
    - Exécution des tests unitaires.
    - Génération des rapports de couverture JaCoCo.
    - Génération de la documentation Javadoc.
    - Publication des artefacts sur GitHub Pages.

2. **Workflow : Docker**
    - Création et test de l'image Docker.
    - Publication de l'image sur Docker Hub.

---

## Installation et configuration

### Prérequis
- Java 17 ou version supérieure
- Maven 3.8 ou version supérieure
- Docker (optionnel pour les déploiements conteneurisés)

### Étapes d'installation

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/micjazer/Tour-Guide.git
   ```

2. Installez les dépendances JAR externes dans votre dépôt Maven local :
   ```bash
   mvn install:install-file -Dfile=./libs/gpsUtil.jar -DgroupId=gpsUtil -DartifactId=gpsUtil -Dversion=1.0.0 -Dpackaging=jar
   mvn install:install-file -Dfile=./libs/RewardCentral.jar -DgroupId=rewardCentral -DartifactId=rewardCentral -Dversion=1.0.0 -Dpackaging=jar
   mvn install:install-file -Dfile=./libs/TripPricer.jar -DgroupId=tripPricer -DartifactId=tripPricer -Dversion=1.0.0 -Dpackaging=jar
   ```

3. Compilez le projet avec Maven :
   ```bash
   mvn clean install
   ```

4. Exécutez l'application :
   ```bash
   java -jar target/tourGuide-0.0.1-SNAPSHOT.jar
   ```

---

## API Endpoints

Voici une liste des principales API exposées par l'application :

| Méthode | Endpoint                              | Description                             |
| ------- | ------------------------------------- | --------------------------------------- |
| GET     | `/getLocation?userName={name}`        | Récupère la position actuelle d’un utilisateur. |
| GET     | `/getNearbyAttractions?userName={name}` | Liste des attractions proches basées sur la position utilisateur. |
| GET     | `/getRewards?userName={name}`         | Récupération des points de récompenses accumulés. |
| POST    | `/tripDeals`                          | Génère des offres de voyage basées sur les préférences utilisateur. |

---

## Contribution

Les contributions sont toujours les bienvenues. Si vous souhaitez contribuer, suivez ces étapes :

1. Forkez le dépôt.
2. Créez une branche pour votre fonctionnalité :
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Effectuez vos modifications et créez une *pull request* avec une description claire.

---

## Auteur

Projet développé par [micjazer](https://github.com/micjazer).

---

## Licence

Ce projet est sous licence MIT. Consultez le fichier `LICENSE` pour plus d’informations.