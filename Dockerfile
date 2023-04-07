# Set the base image as the official OpenJDK image with JDK 11
FROM openjdk:11-jdk-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy gradlew and gradle wrapper files
COPY gradlew ./
COPY gradle ./gradle

# Grant execute permissions to gradlew script
RUN chmod +x ./gradlew

# Copy build files (build.gradle, settings.gradle, etc.)
COPY build.gradle settings.gradle ./

# Copy the source code into the container
COPY src ./src

# Run the Gradle wrapper bootJar task
RUN ./gradlew clean bootJar --no-daemon

# Set the base image as the official OpenJDK image with JRE 11
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage into the container
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application's port
EXPOSE 8080

# Set the entrypoint to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
