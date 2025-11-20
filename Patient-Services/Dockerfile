# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8089
EXPOSE 9001

ENTRYPOINT ["java", "-jar", "app.jar"]

