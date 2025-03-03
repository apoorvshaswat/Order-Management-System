# Step 1: Use a Gradle image to build the project
FROM gradle:7.4.0-jdk17 AS build

WORKDIR /app

COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .

RUN ./gradlew build --no-daemon

COPY src ./src

RUN ./gradlew clean build --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
