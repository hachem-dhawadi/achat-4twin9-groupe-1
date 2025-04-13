# Base image
FROM openjdk:8-jdk-slim

# Metadata
LABEL maintainer="your-email@example.com"

# Copy the packaged JAR file
COPY target/achat-1.0.jar app.jar

# Expose the port used by the application (from application.properties)
EXPOSE 8089

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]