FROM openjdk:21-slim

WORKDIR /app

COPY target/*.jar /app/api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api.jar"]