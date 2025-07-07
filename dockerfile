FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/tourguide-0.0.1-SNAPSHOT.jar /app/tourguide-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/tourguide-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
