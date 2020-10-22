# Spring Clean Architecture [![Build Status](https://travis-ci.com/pdcmb/java-spring-clean-architecture.svg?branch=master)](https://travis-ci.com/pdcmb/java-spring-clean-architecture)

## Table of Content

1. [Description](#description)
2. [Usage](#usage)
    2.1. [Routes](#routes)
    2.2. [Parameters](#parameters)
    2.3. [Filters](#filters)
3. [Structure](#structure)

## Description <a id="description"></a>

This simple java application allows to retrevie data on Coronavirus in different regions of the world. It provides simple REST(ish) [^2] api interface. Data are retrieved from [Covid19api] and are aggregated by regions.

## Usage <a id="usage"></a>

To install it simply clone this repository and than build this project using gradle

### Routes <a id="routes"></a>

Possible routes are:

Method | Uri | Description |
------------ | ------------- | ----------- |
GET | /regions  | Returns all regions available
GET | /region/:region?filter  | Returns data for a given region
GET | /region/metadata | Returns metadata for regions dataset
GET | /region/stats?field?filter | Returns statistics for regions dataset

Where:
* **:param** is path variable
* **?param** is url query.

For instance for parameter :region = "eu" URL would be :

    /region/eu

while for ?field="confirmed"

    /stats?field="confirmed"

Of course they can be combined so, for instance, you can fetch a region by specifiyng :region parameter and then pass filter in url query:

    /region/eu?filter={"confimed":{"$gt":1000}}

### Parameters <a id="parameters"></a>

Parameter | Required |Descritpion | Possible values | 
------------ | ------------- | ------------- | ------------- |
:name |  :white_check_mark: |region code |"eu", "middle-east"  |
?field | :white_check_mark: |region code | use /metadata route to get all fieds for given dataset  | 
?filter | :x: |region code | see [filters](#filters)  |  

### Filters <a id="filters"></a>

You can add filter to yo request to filter retrivied data. 


Operator | alias |
------------ | ------------- | 
Greater than |  gt |
Less than |  lt |
Equal |  eq |
Beetween |  bt |



Operator beetwen (bt) requires an array of exactly **two** elements 
        
    [initial value, final value] 

## Structure <a id="structure"></a>

The architecture of the project follows the principles of Clean Architecture [^1]. It coinsist of multiple layers, each of which rappresents different part of the application. 


### Packages <a id="structure"></a>

The project coinsist of four modules (plus utilities):
 * Configuration
 * Presentation
 * Domain
 * Data

 ![packages](https://user-images.githubusercontent.com/19626498/96870420-2a642c00-1471-11eb-973e-54168f1411ca.png)

#### Core <a id="structure"></a>

This module contains the domain entities and use cases. It contains the business rules that are essential for our application and therefore rappresents core and most inner part of the architecture and for that reason it should have no dependency on onther components.
In other words, i 



According to Clean Architecture principles by Uncle Bob, domain should not have dependencies to frameworks and/or external libraries, but (for now) it uses reactor and spring DI libraries.

#### Data <a id="structure"></a>

This layer is reposponsible of retrievieng data from varius data sources (databases, remote api etc.). 

#### Presentation <a id="structure"></a>

This layer can be considered front-end of our application, it accepts request and exposes data to the outside. 

## Tech 

* [Spring Framework] - Framework for creating production-grade applications that run on JVM
* [Spring Boot] - Tool that makes developing web application and microservices with Spring Framework faster and easier through 
* [Reactor] - Tool for building a stand-alone Spring-based application with minimal or zero configurations
* [Covid19api] - A free API for data on the Coronavirus


  [^1]: [The Clean Architecture] by Uncle Bob.
  [^2]: Obviously it is not fully RESTfull

  [The Clean Architecture]: https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
  [Spring Framework]: https://spring.io/
  [Spring Boot]: https://spring.io/projects/spring-boot
  [Reactor]: https://projectreactor.io/ 
  [Covid19api]: https://covid19api.com/

