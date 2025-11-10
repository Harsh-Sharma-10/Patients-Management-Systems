# Stage 1: Build the app with Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY Patient-Services/pom.xml .
COPY Patient-Services/src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:21-jdk AS runner

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "app.jar"]
