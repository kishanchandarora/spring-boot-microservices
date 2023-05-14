- Microservices
    ![micro services](./images/Screenshot%20(89).png)

- Prerequisite for microservice tutorial
    - Spring Boot
    - Project using monolithic architecture
        - Smart contact manager
        - Exam portal
        - Blog application
    - ![prerequisite](./images/Screenshot%20(90).png)


- Before microservices
    - Monolithic architecture: multiple components are combined in single large app. Ex: Joint family lives in single house
    - Single code base
    - Deployed in single bundle
    - Change in one service then whole app is redeployed
        - If one service is down due to which whole application is down
        - Change is one service for that need to redeployed whole application
    - Building problem: developers has to communicate
    - Problem in scale
        - Single project can't handle multiple requests
    - Cumbersome over time
    - ![before microservices](./images/Screenshot%20(91).png)

- In microservices
    - Large apps are divide into small parts
        - Service can communicate with the help of REST APIs, also with help of broken for async calls
    - Different codebase
    - Each module managed independently
        - Ex: Every family has its own house
    - Different tech stack
    - Disadvantage
        - Handling microservices in complex
        - If you userbase is small so no need to go for microservices
    - ![in microservices](./images/Screenshot%20(92).png)

- Microservices architecture
    - ![microservices architecture](./images/Screenshot%20(94).png)
    - One microservice is equal to one spring boot project

- Service Registry
    - It is previous used by netflix and spring boot embed this as a dependency
    - All service(instances) are registered here
    - Track all the information of services means which service is down or up
    - We can call the services by its name, even if services ip's are changed
    - This also handled Load Balancing
    - For server we need
        - cloud bootstrap
        ```xml
            <properties>
		        <spring-cloud.version>2021.0.7</spring-cloud.version>
	        </properties>

        	<dependency>
			    <groupId>org.springframework.cloud</groupId>
			    <artifactId>spring-cloud-starter</artifactId>
		    </dependency>

            <!-- It is important -->
            <dependencyManagement>
                <dependencies>
                    <dependency>
                        <groupId>org.springframework.cloud</groupId>
                        <artifactId>spring-cloud-dependencies</artifactId>
                        <version>${spring-cloud.version}</version>
                        <type>pom</type>
                        <scope>import</scope>
                    </dependency>
                </dependencies>
	        </dependencyManagement>
        ```
        
        - Eureka server: for eureka server we need above cloud bootstrap dependency also
        ```xml
        	<dependency>
			    <groupId>org.springframework.cloud</groupId>
			    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		    </dependency>
        ```
        - code of eureka server 
        ```java
            @SpringBootApplication
            @EnableEurekaServer
            public class ServiceRegistryApplication {

                public static void main(String[] args) {
                    SpringApplication.run(ServiceRegistryApplication.class, args);
                }

            }
        ```
         ```yml
            server:
                port: 8761 # default port for eureka server

            eureka:
                instance:
                    hostname: localhost

                client: # default values are true
                    fetch-registry: false
                    register-with-eureka: false
        ```

        - Eureka client:  for eureka server we don't need cloud bootstrap dependency
        ```xml
             <!-- It is important -->
             <properties>
		        <spring-cloud.version>2021.0.7</spring-cloud.version>
	        </properties>

            <dependencyManagement>
                <dependencies>
                    <dependency>
                        <groupId>org.springframework.cloud</groupId>
                        <artifactId>spring-cloud-dependencies</artifactId>
                        <version>${spring-cloud.version}</version>
                        <type>pom</type>
                        <scope>import</scope>
                    </dependency>
                </dependencies>
	        </dependencyManagement>

            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            </dependency>
        ```
        - code of eureka client 
        ```java
            @SpringBootApplication
            @EnableEurekaClient
            public class UserServiceApplication {

                public static void main(String[] args) {
                    SpringApplication.run(UserServiceApplication.class, args);
                }

            }
        ```
        ```yml
            server:
                port: 8081
            
            spring:
                application:
                    name: USER-SERVICE

            eureka:
                instance:
                    prefer-ip-address: true

                client: # default values are true
                    fetch-registry: true
                    register-with-eureka: true
                    service-url:
                        defaultZone: http://localhost:8761/eureka
        ```
    - ![service registry](./images/Screenshot%20(97).png)
    - ![service communication](./images/Screenshot%20(98).png)
    - Calling microservice using code
    ```java
        @Configuration
        public class MyConfig {

            @Bean
            @LoadBalanced // call using service name we need this
            public RestTemplate getRestTemplate() {
                return new RestTemplate();
            }
        }
    ```

- Lets start building microservices
    - ![building microservices](./images/Screenshot%20(99).png)

* Snapshot versions of spring boot are under developed, so we don't use it

    
    