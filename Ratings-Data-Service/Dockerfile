FROM openjdk:14-jdk-alpine

LABEL maintainer="Ash"

COPY /target/Ratings-Data-Service-0.0.1-SNAPSHOT.jar /Ratings-Data-Service-0.0.1-SNAPSHOT.jar

EXPOSE 8082:8082

CMD ["java", "-jar", "/Ratings-Data-Service-0.0.1-SNAPSHOT.jar"]