FROM eclipse-temurin:25-jdk AS build
WORKDIR /src
COPY . .
RUN chmod +x ./gradlew && ./gradlew clean build --no-daemon

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /src/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# HOW TO RUN - in terminal
# STEP 1: "./gradlew build"
# STEP 2: "docker build -t your-image-name . "
# STEP 3: "docker run -p 8080:8080 your-image-name"

# Make sure you are in the Project02 directory...