- Microservices

- Prerequisite for microservice tutorial
    - Spring Boot
    - Project using monolithic architecture
        - Smart contact manager
        - Exam portal
        - Blog application

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
    - { image import here }

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
    - { image import here }

- Microservices architecture
    - { image import here }
    - One microservice is equal to one spring boot project

- Service Registry
    - All service(instances) are registered here
    - Track all the information of services means which service is down or up
    - We can call the services by its name, even if services ip's are changed
    - This also handled Load Balancing
    - { image import here }

- Lets start building microservices
    - { image import here }

* Snapshot versions of spring boot are under developed, so we don't use it

    
    