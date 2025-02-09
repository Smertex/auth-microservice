FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY src src

RUN chmod +x gradlew && ./gradlew build -x test

FROM eclipse-temurin:21-jre-alpine
ARG DEPENDENCY=/app/build
COPY --from=build ${DEPENDENCY}/libs /app/lib

RUN mv /app/lib/*SNAPSHOT.jar /app/lib/app.jar || true

ENTRYPOINT ["java", "-jar", "/app/lib/app.jar"]