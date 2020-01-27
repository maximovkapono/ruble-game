FROM openjdk:8
ADD target/rouble-game.jar rouble-game.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","rouble-game.jar"]