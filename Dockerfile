FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get upgrade -y

LABEL author = "Hemanth Chirikonda"

WORKDIR /app

COPY target/carDealer-0.0.1-SNAPSHOT.jar  /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

