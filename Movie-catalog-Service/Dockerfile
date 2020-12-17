FROM openjdk:14-jdk-alpine

LABEL maintainer="Ash"

COPY /target/Movie-catalog-Service-0.0.1-SNAPSHOT.jar /Movie-catalog-Service-0.0.1-SNAPSHOT.jar

EXPOSE 8081:8081

CMD ["java", "-jar", "/Movie-catalog-Service-0.0.1-SNAPSHOT.jar"]