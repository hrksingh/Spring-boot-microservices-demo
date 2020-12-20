<h1><span style="color:Green "> Spring-boot-microservices-demo </span></h1>


**Eureka + Hystrix + circuit breaker+ Zuul + docker**

To connect all microservices with common bridge network
>docker network create spring-network
- <span style="color:Red "> **No need to use this 👆 if you are using docker compose**

To build Docker Image from file:
>docker build -t zuulserver:1.0 . <span style="color:Grey ">              # dot here refer to dockerfile in current directory


To start Container:
>docker container run --network spring-network --name eurekaServer -p 8761:8761 -d eurekaserver:1.0

To start all Services
>docker-compose up

To stop all Services
>docker-compose down


-----
 🔴IMPORTANT🔴 🔥
  - Remember if your service cannot connect to the internet or not available to make api call it might be due to SSL and TLS certificate issue. You can install this via DockerFile to see example visit this [dockerfile](https://github.com/hrksingh/Spring-boot-microservices-demo/blob/main/Zuul/Dockerfile)


❗ But If you are using your own network it still may not connect, to solve this. See this [topic ⬇](#enable-forwarding-from-docker-containers-to-the-outside-world)



---
## Enable forwarding from Docker containers to the outside world

By default, traffic from containers connected to the default bridge network is not forwarded to the outside world. To enable forwarding, you need to change two settings. These are not Docker commands and they affect the Docker host’s kernel.

1. <I>Configure the Linux kernel to allow IP forwarding.</I>

  ```sh
     $ sudo sysctl net.ipv4.conf.all.forwarding=1
```


2. Change the policy for the iptables <B>```FORWARD```</B> policy from <B>``` DROP ```</B> to <B>```ACCEPT```</B>.

```sh
  $ sudo iptables -P FORWARD ACCEPT
```


<span style="color:Yellow "> <font size="4"> ⚠ </font></span>&nbsp; These settings do not persist across a reboot, so you may need to add them to a start-up script.

-----

To test project -> http://localhost:8080/api/catalog/U1 
