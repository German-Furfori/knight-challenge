FROM openjdk:11-jdk-slim

WORKDIR /knight_board

COPY target/knight-challenge-1.0-jar-with-dependencies.jar knight_board.jar

ENTRYPOINT ["java", "-jar", "knight_board.jar"]