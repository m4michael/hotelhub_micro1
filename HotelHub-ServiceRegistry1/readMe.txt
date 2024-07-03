Eureka Server acts as a service registry where microservices can register themselves and discover each other. 
To set up and run an Eureka Server, you use Spring Cloud, 
which provides the necessary tools and dependencies to create and manage the server.
-----------------------------------------
In application.properites:


eureka.client.register-with-eureka=false: 
This line prevents the Eureka Server from registering itself as a client in its own registry.

eureka.client.fetch-registry=false: 
This line prevents the Eureka Server from fetching the registry information from itself or any other Eureka server.
------------------------------------------