FROM openjdk:14-jdk-alpine

LABEL maintainer="Ash"

RUN apk update
                                                                                                                                                                                                                      
RUN apk add ca-certificates wget \                                                                                                                                                                                                      
    && update-ca-certificates

RUN apk add openssl

RUN apk add ca-certificates openssl \                                                                                                                                                                                                      
    && update-ca-certificates

COPY /target/Zuul-demo-0.0.1-SNAPSHOT.jar /Zuul-demo-0.0.1-SNAPSHOT.jar

EXPOSE 8080:8080

CMD ["java", "-jar", "/Zuul-demo-0.0.1-SNAPSHOT.jar"]