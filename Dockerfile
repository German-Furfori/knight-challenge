FROM maven:3.9.6-eclipse-temurin-11 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim

WORKDIR /knight_board

COPY --from=builder /app/target/knight-challenge-1.0-jar-with-dependencies.jar knight_board.jar

ENTRYPOINT ["java", "-jar", "knight_board.jar"]