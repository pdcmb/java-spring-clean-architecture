# Getting Started



## Usage 


### Routes

Method | Uri | Description |
------------ | ------------- | ----------- |
GET | /regions  | Returns all regions
GET | /region/:region  | Returns data for a given region (or for all regions)
GET | /region/metadata | Returns metadata for regions dataset
GET | /region/stats | Returns statistics for regions dataset
GET | /countries | Returns all countries
GET | /country/:country | Returns statistics for regions dataset
GET | /country/metadata | Returns statistics for countries dataset
GET | /country/stats | Returns statistics for countries dataset

### Parameters

Parameter | Descritpion | Possible values | 
------------ | ------------- | ------------- |
:name |  region code |"eu", "middle-east"  | 


### Filters

filter | description | 
------------ | ------------- | 
:name | "all", "eu", "middle-east"  | 

## Structure

The architecture of the project follows the principles of Clean Architecture [^1]


### Packages

The project coinsist of four modules:
 * Configuration,
 * Presentation,
 * Domain
 * Data

#### Core

This module contains the domain entities and use cases. This module contains the business rules that are essential for our application. In this module, gateways for the repositories are also being defined.

According to Clean Architecture principles by Uncle Bob,  this component should not have dependencies to frameworks and/or external libraries, but for now it uses reactor and spring DI libraries.

#### Data



## Tech 

* [Spring Framework] - Framework for creating production-grade applications that run on JVM
* [Spring Boot] - Tool that makes developing web application and microservices with Spring Framework faster and easier through 
* [Reactor] - Tool for building a stand-alone Spring-based application with minimal or zero configurations

## To do


  [^1]: [The Clean Architecture] by Uncle Bob.

  [The Clean Architecture]: https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
  [Spring Framework]: https://spring.io/
  [Spring Boot]: https://spring.io/projects/spring-boot
  [Reactor]: https://projectreactor.io/ 

