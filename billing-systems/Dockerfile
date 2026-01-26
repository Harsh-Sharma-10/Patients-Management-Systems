FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy JAR file (adjust name if needed)
COPY target/billing-systems-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 9001
EXPOSE 8090

ENV TZ=Asia/Kolkata
ENV JAVA_OPTS="-Duser.timezone=Asia/Kolkata"


ENTRYPOINT ["java", "-Duser.timezone=Asia/Kolkata", "-jar", "app.jar"]