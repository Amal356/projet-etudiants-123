# Multi-stage build for Grading Service
# Stage 1: Build stage using Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage using JRE
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the JAR file from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8081
EXPOSE 8081

# Set the entrypoint to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
