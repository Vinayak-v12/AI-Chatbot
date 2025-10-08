# Use OpenJDK 21 runtime
FROM openjdk:21-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the compiled JAR into the container
COPY target/Ai-chatbot-0.0.1-SNAPSHOT.jar myapp.jar

# Run the application
ENTRYPOINT ["java", "-jar", "myapp.jar"]