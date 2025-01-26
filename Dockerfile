# Use a base image with Maven and JDK
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Build the project using Maven
RUN mvn clean package

# Use a smaller image to run the application
FROM openjdk:11-jre-slim

# Set the working directory for the running application
WORKDIR /app

# Copy the built JAR file from the build stage to the runtime container
COPY COPY target/*.jar app.jar

# Set the command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]

# Expose the application port (adjust this based on your app)
EXPOSE 8080
