# Use a base image that has JDK installed
#FROM openjdk:11-jre-slim

# Set the working directory inside the container
#WORKDIR /app

# Copy the JAR file from the target directory into the container
#COPY target/PetStoreAutomation-0.0.1-SNAPSHOT.jar app.jar


# Expose the port your application will run on
#EXPOSE 8080

# Command to run the JAR file
#ENTRYPOINT ["java", "-jar", "app.jar"]
# Use a base image with Maven and JDK installed
FROM maven:3.9.4-eclipse-temurin-17

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the project and run the tests
ENTRYPOINT ["mvn", "test"]
