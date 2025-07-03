# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR into the container
COPY target/discovery-server-0.0.1-SNAPSHOT.jar discovery.jar

# Expose Eureka default port
EXPOSE 8761

# Command to run the Eureka server
ENTRYPOINT ["java", "-jar", "discovery.jar"]
