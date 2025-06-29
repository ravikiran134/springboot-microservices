FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/employee-service-0.0.1-SNAPSHOT.jar employee.jar
ENTRYPOINT ["java", "-jar", "employee.jar"]