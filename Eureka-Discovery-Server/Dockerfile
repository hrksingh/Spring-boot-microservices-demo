FROM openjdk:14-jdk-alpine

LABEL maintainer="Ash"

COPY /target/Eureka-Discovery-Server-0.0.1-SNAPSHOT.jar /Eureka-Discovery-Server-0.0.1-SNAPSHOT.jar

EXPOSE 8761:8761

CMD ["java", "-jar", "/Eureka-Discovery-Server-0.0.1-SNAPSHOT.jar"]