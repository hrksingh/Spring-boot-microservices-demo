# Spring-boot-microservices-demo
Eureka + Hystrix + circuit breaker+ Zuul

To connect all microservices with common bridge network
>docker network create spring-network

To build Docker Image from file:
>docker build -t zuulserver:1.0 . #-> dot here refer to dockerfile in current directory

To start Conatiner:
❯ docker container run --network spring-network --name eurekaServer -p 8761:8761 -d eurekaserver:1.0



