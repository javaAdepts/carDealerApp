# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim


RUN apt-get update && apt-get upgrade -y

LABEL author = "Hemanth Chirikonda"

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/carDealer-0.0.1-SNAPSHOT.jar  /app/app.jar

# Expose the port your application runs on
EXPOSE 8080


# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

