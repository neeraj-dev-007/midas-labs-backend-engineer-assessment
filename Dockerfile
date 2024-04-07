FROM gradle:8.7.0-jdk21 AS builder

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle/ gradle/

RUN ./gradlew --version

COPY . .

RUN ./gradlew clean build

FROM eclipse-temurin:21 AS run

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

CMD ["java","-jar","app.jar"]