# Use Eclipse Temurin base image with JDK 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file built by Maven (make sure to run mvn package before pushing)
COPY target/*.jar app.jar

# Expose the port Spring Boot runs on
EXPOSE 8080

# Start the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]