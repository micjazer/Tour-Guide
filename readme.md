# TourGuide Application

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.15-green)
![Docker](https://img.shields.io/badge/Docker-ready-blue)
![JaCoCo](https://img.shields.io/badge/Test%20Coverage-JaCoCo-red)

### GitHub Pages Documentation

Project documentation is available on GitHub Pages:
- [GitHub Pages](https://fraigneau.github.io/Fraigneau-Lucas-P8-java/)
  - [JaCoCo Coverage Report](https://fraigneau.github.io/Fraigneau-Lucas-P8-java/site/jacoco/)
  - [JavaDoc](https://fraigneau.github.io/Fraigneau-Lucas-P8-java/site/apidocs/)
  - [Surefire Test Report](https://fraigneau.github.io/Fraigneau-Lucas-P8-java/reports/surefire.html)

## Overview

TourGuide is a Java application that provides tourists with recommendations for attractions, guides, and trip deals based on their location. This application integrates with external services for location tracking, rewards calculation, and trip pricing to provide personalized recommendations to users.

## Features

- **User Location Tracking**: Track and update user locations in real-time
- **Nearby Attractions**: Identify attractions near a user's current location
- **Reward Points**: Calculate and track reward points for user visits to attractions
- **Trip Deals**: Generate personalized trip deals based on user preferences
- **Performance Optimization**: Multi-threading support for handling large number of users efficiently

## Project Structure

```
TourGuide/
├── src/
│   ├── main/java/tourGuide/
│   │   ├── controller/     # REST API controllers
│   │   ├── helper/         # Helper classes
│   │   ├── service/        # Business logic services
│   │   ├── model/          # Data models
│   │   ├── user/           # User management 
│   │   ├── tracker/        # Location tracking
│   │   └── TourGuideApplication.java  # Main application
│   └── test/              # Test classes
├── libs/                  # External JAR dependencies
├── Dockerfile             # Docker configuration
├── .github/workflows/     # CI/CD pipeline configurations
├── pom.xml                # Maven dependencies and build configuration
└── README.md              # Project documentation
```

## Technical Stack

- **Java 17**: Core programming language
- **Spring Boot**: Application framework
- **Maven**: Dependency management and build
- **JUnit & Mockito**: Testing framework
- **JaCoCo**: Code coverage
- **Docker**: Containerization
- **GitHub Actions**: CI/CD pipeline

## External Dependencies

The application uses three external JAR libraries (located in the `libs/` directory):

1. **gpsUtil.jar**: Provides GPS-based location services
2. **RewardCentral.jar**: Manages computation of user rewards
3. **TripPricer.jar**: Provides pricing for trip packages

## CI/CD Pipeline

The project uses GitHub Actions for continuous integration and deployment:

### 1. Generate Artifacts Workflow
- Builds the application
- Runs unit tests
- Generates JaCoCo coverage reports
- Generates JavaDoc documentation
- Publishes artifacts to GitHub Pages

### 2. Docker Workflow
- Builds the application
- Creates Docker image
- Pushes Docker image to Docker Hub

## Setup and Installation

### Prerequisites
- Java 17 or higher
- Maven 3.8 or higher
- Docker (optional, for containerized deployment)

### Local Setup

1. Clone the repository:
   ```
   git clone https://github.com/fraigneau/Fraigneau-Lucas-P8-java.git
   cd Fraigneau-Lucas-P8-java
   ```

2. Install the external JAR dependencies:
   ```bash
   mvn install:install-file -Dfile=./libs/gpsUtil.jar -DgroupId=gpsUtil -DartifactId=gpsUtil -Dversion=1.0.0 -Dpackaging=jar
   ```
   ```bash
   mvn install:install-file -Dfile=./libs/RewardCentral.jar -DgroupId=rewardCentral -DartifactId=rewardCentral -Dversion=1.0.0 -Dpackaging=jar
   ```
   ```bash
   mvn install:install-file -Dfile=./libs/TripPricer.jar -DgroupId=tripPricer -DartifactId=tripPricer -Dversion=1.0.0 -Dpackaging=jar
   ```

3. Build the application:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   java -jar target/tourGuide-0.0.1-SNAPSHOT.jar
   ```

### Docker Setup

The Docker image is available on Docker Hub: [fraigneau/p8](https://hub.docker.com/r/fraigneau/p8)

1. Pull the Docker image:
   ```
   docker pull fraigneau/p8:latest
   ```

2. Run the container:
   ```
   docker run -p 8080:8080 fraigneau/p8:latest
   ```

3. Or use a specific version (using commit SHA):
   ```
   docker pull fraigneau/p8:[commit-sha]
   docker run -p 8080:8080 fraigneau/p8:[commit-sha]
   ```

## API Endpoints

| Method | Endpoint                              | Description                            |
| ------ | ------------------------------------- | -------------------------------------- |
| GET    | /getLocation?userName={name}          | Get a user's location                  |
| GET    | /getNearbyAttractions?userName={name} | Get attractions near the user          |
| GET    | /getRewards?userName={name}           | Get user's reward points               |
| GET    | /getAllCurrentLocations               | Get all users' current locations       |
| GET    | /getTripDeals?userName={name}         | Get personalized trip deals for a user |
| GET    | /home                                 | Simple greeting page (for testing)     |

## Testing 

!!! ADD Time !!!

### Running Tests Locally

Run tests with Maven:
```
mvn test
```

Generate test coverage report:
```
mvn jacoco:report
```

The JaCoCo report will be available at `target/site/jacoco/index.html`.

## Performance Optimization

The application includes performance optimizations for handling large numbers of users:

- **Concurrent Processing**: Uses Java's Fork/Join framework for parallel execution
- **Thread Pools**: Manages resources efficiently for concurrent operations
- **Optimized Reward Calculation**: Prevents redundant calculations

## GitHub Repository

The source code is available on GitHub: [fraigneau/Fraigneau-Lucas-P8-java](https://github.com/fraigneau/Fraigneau-Lucas-P8-java)

## License

This project is licensed under the MIT License.

## Contributors

- Lucas Fraigneau