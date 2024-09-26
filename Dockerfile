# Use an official Maven image with Eclipse Temurin JDK 17 as the base image for building the project
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and any other necessary Maven files for dependency resolution
COPY pom.xml .

# Download dependencies (helps to cache dependencies and speed up builds)
RUN mvn dependency:go-offline -B

# Copy the entire project source code into the container
COPY src ./src

# Package the project (create a fat JAR using the Maven Shade plugin as defined in your POM)
RUN mvn package -DskipTests

# Use an OpenJDK 11 image to run the JAR
FROM openjdk:11-jre-slim

# Set the working directory for the runtime
WORKDIR /app

# Copy the built JAR file from the Maven build stage into this stage
COPY --from=build /app/target/my-project-1.0-SNAPSHOT.jar ./app.jar

# Expose the port the application will run on (optional, adjust based on your app)
EXPOSE 8080

# Set the default command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
