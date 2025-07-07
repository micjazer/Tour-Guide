FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/tourguide-0.0.1-SNAPSHOT /app/tourguide-0.0.1-SNAPSHOT

ENTRYPOINT ["java", "-jar", "/app/my-app.jar"]

EXPOSE 8080
