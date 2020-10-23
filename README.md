# Spring Clean Architecture [![Build Status](https://travis-ci.com/pdcmb/java-spring-clean-architecture.svg?branch=master)](https://travis-ci.com/pdcmb/java-spring-clean-architecture)

## Table of Content

1. [Description](#description)
2. [Usage](#usage)
    1. [Routes](#routes)
    2. [Parameters](#parameters)
    3. [Filters](#filters)
3. [Structure](#structure)
    1. [Packages](#packages)
    2. [Packages](#packages)
        1. [Core](#core)
        2. [Data](#data)
        3. [Filters](#presentation)
2. [Reactive approach](#reactive)

## Description <a id="description"></a>

This simple java application allows to retrevie data on Coronavirus in different regions of the world. It provides simple REST(ish) <a id="ft-1-ref" href="#ft-1"><sup>[1]</sup></a> api interface. Data are retrieved from [Covid19api] and are aggregated by regions.


**Note**: On start the application makes an initial request to [Covid19api] to get all available data, since they need to be transformed and requests to get data for every single country in a region need to be delyed (otherwise they will be blocked), it might take some time (few minutes). Data then are stored in memory so all subsequent request should be fast, however if data is not found in cache, application could make another long request to fetch all data.

## Usage <a id="usage"></a>

To install simply clone this repository and than build it using gradle

### Routes <a id="routes"></a>

Possible routes are:

Method | Uri | Description | URL parameters |
------------ | ------------- | ----------- |  ----------- | 
GET | /regions  | Returns all regions available | none
GET | /region/metadata | Returns metadata for regions dataset | none
GET | /region/:region  | Returns data for a given region | ?filter
GET | /region/:region/latest  | Returns latest data for that region | none
GET | /region/:region/stats | Returns statistics for regions dataset | ?field, ?filter 
GET | /region/:region/delta  | Returns daily increment of data | ?filter
GET | /region/:region/delta/stats | Returns statistics about daily delta | ?field, ?filter

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
:name |  :heavy_check_mark: |region code |"eu", "middle-east", all  |
?field | :heavy_check_mark: |region code | confirmed, deaths, recovered, active  | 
?filter | :x: |region code | see [filters](#filters)  |  

### Filters <a id="filters"></a>

You can add filter to yo request to filter retrivied data. 


Operator | alias |
------------ | ------------- | 
Greater than |  gt |
Less than |  lt |
Equal |  eq |
Beetween |  bt |

A filter needs to have a following structure:

    { "field" : { "$operator": value } }

Operator beetwen (bt) requires value an array of exactly **two** elements, in this case filter becomes:  
        
    { "field" : { "$operator": [initial value, final value]  } }

## Use case diagram

![usecase](https://user-images.githubusercontent.com/19626498/97039709-21588500-156d-11eb-97be-582dbf415f3d.png)

## Sequence diagram

Basic request has following diagram

![sequence](https://user-images.githubusercontent.com/19626498/97049755-82d42000-157c-11eb-8bd6-924b67e0ea0f.png)

## Structure <a id="structure"></a>

The architecture of the project follows the principles of Clean Architecture <a id="ft-2-ref" href="#ft-2"><sup>[2]</sup></a>. It coinsist of multiple layers, each of which rappresents different part of the application.

![clean](https://user-images.githubusercontent.com/19626498/96997399-fef54600-1531-11eb-813b-2f1295b7d18e.png)

The most inner layer rappresent the core of our application, it tells us **what** does the application do while outer layers are implementation details, they tell **how** it is done. 

### Packages <a id="packages"></a>

The project coinsist of four modules (plus utilities):
 * Configuration
 * Presentation
 * Domain
 * Data

![packages](https://user-images.githubusercontent.com/19626498/96997550-3e239700-1532-11eb-8664-3e6b6161caed.png)

Since domain has no dependencies on outer layers, it's doesn't need to be modified if we change other component. It provides better testability and modularity, we can, for instance, swap one repository with another, since domain doesn't know anything about implementation details and does't care where it does get data from as long, as they implement exposed interfaces

#### Core <a id="core"></a>

This module contains the domain entities and use cases. It contains the business rules that are essential for our application and therefore rappresents core and most inner part of the architecture and for that reason it should have no dependency on other components. 


According to Clean Architecture principles by Uncle Bob, domain should not have dependencies to frameworks and/or external libraries, but in out case (for now) it uses reactor and spring DI libraries.

#### Data <a id="data"></a>

This layer defines how data are stored and managed and retrieved from different data sources. It provides implementations of repositories interfaces defined inside domain.
In this module are also defined different data sources rappresentaions which are responsible for storing and retriving data from given source.  

#### Presentation <a id="presentation"></a>

This layer can be considered front-end of our application, it accepts request and exposes data to the outside.
It defines how data are presented, exposed to the end user, so it has to format data to desired format.

## Reactive approach <a id="reactive"></a>

Reactive programming is a programming paradigm that promotes an asynchronous, non-blocking, event-driven approach to data processing.
Reactive programming involves modeling data and events as observable data streams and implementing data processing routines to react to the changes in those streams.

![flow](https://user-images.githubusercontent.com/19626498/96936781-ffa4c280-14c6-11eb-9e72-0c9ff65a8f24.png)

When user request a resource, it's request starts inside presentation layer, and it's propagated to data layer. Data layer produces a data stream (Flux or Mono in Reactor) to which presentation layer subscribes, listening to it for values.



## Tech 

* [Spring Framework] - Framework for creating production-grade applications that run on JVM
* [Spring Boot] - Tool that makes developing web application and microservices with Spring Framework faster and easier through 
* [Reactor] - Tool for building a stand-alone Spring-based application with minimal or zero configurations
* [Covid19api] - A free API for data on the Coronavirus

<hr>

  
  <a id="ft-1" href="#ft-1-ref"><sup>[1]</sup></a>. Obviously it is not fully RESTfull<br />
  <a id="ft-2" href="#ft-2-ref"><sup>[2]</sup></a>. [The Clean Architecture] by Uncle Bob.

  [The Clean Architecture]: https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
  [Spring Framework]: https://spring.io/
  [Spring Boot]: https://spring.io/projects/spring-boot
  [Reactor]: https://projectreactor.io/ 
  [Covid19api]: https://covid19api.com/

