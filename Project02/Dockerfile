FROM eclipse-temurin:25-jre
WORKDIR /app
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# HOW TO RUN - in terminal
# STEP 1: "./gradlew build"
# STEP 2: "docker build -t your-image-name . "
# STEP 3: "docker run -p 8080:8080 your-image-name"