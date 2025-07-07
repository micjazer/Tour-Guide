FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/my-app.jar /app/my-app.jar

ENTRYPOINT ["java", "-jar", "/app/my-app.jar"]

EXPOSE 8080
