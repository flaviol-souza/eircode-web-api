This project is API service to query for addresses based a address code, based are endpoints:
* https://developers.alliescomputing.com/postcoder-web-api/address-lookup/eircode
* https://developers.alliescomputing.com/postcoder-web-api/address-lookup/premise

The API is compatible with endpoints above. the each new request of a address are the data are put in a cache of API.

## Prerequisites:
    Java 1.8
    Maven 3.3.3
    Docker (recommend 17)

## Steps to up start the app, use as start path the origin of project
    ## 1- build back-end (Java)
        cd web-api-eircode
        mvn clean install 

    ## 2- build environment, use as start path the origin of project
        cd ..
        docker-compose build
        docker-compose up

    # 3- Access
        front-end: localhost:3000
            * Api Key: PCW45-12345-12345-1234X
            * Country Code: uk
            * Address Code: NR147PZ

        back-end: localhost:8080
            * http://localhost:8080/pcw/PCW45-12345-12345-1234X/address/uk/NR147PZ?format=xml

## Shutdown app
    press Ctrl+C
    docker-compose down

    #to start app again
    docker-compose up

## Project Details
    ## Web:
        Angular 2
        Node.js
    ## Api (Rest)
        Java 
        Maven
        Spring Boot/Data 
        Mockito
    # Cache
        Redis

## To Do
    - add ability volume mapping of Redis 
    - add test in web project