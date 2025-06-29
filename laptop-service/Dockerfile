FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/laptop-service-0.0.1-SNAPSHOT.jar laptop.jar
ENTRYPOINT ["java", "-jar", "laptop.jar"]