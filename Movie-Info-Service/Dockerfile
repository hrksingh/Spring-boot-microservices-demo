FROM openjdk:14-jdk-alpine

LABEL maintainer="Ash"

COPY /target/Movie-Info-Service-0.0.1-SNAPSHOT.jar /Movie-Info-Service-0.0.1-SNAPSHOT.jar

EXPOSE 8082:8082

CMD ["java", "-jar", "/Movie-Info-Service-0.0.1-SNAPSHOT.jar"]