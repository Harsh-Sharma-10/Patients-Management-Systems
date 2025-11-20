# Use a lightweight JDK image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy JAR file (adjust name if needed)
COPY target/*.jar app.jar


EXPOSE 9001
EXPOSE 8090


ENTRYPOINT ["java", "-jar", "app.jar"]
