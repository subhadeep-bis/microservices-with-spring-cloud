# microservices-with-spring-cloud
## Learning Important topics of Spring Cloud

1.  SPRING CLOUD CONFIG SERVER AND BUS
2.  LOAD BALANCING WITH RIBBON AND FEIGN
3.  IMPLEMENT NAMING SERVER WITH EUREKA
4.  IMPLEMENTING API GATEWAY WITH ZUUL
5.  DISTRIBUTED TRACING WITH ZIPKIN
6.  FAULT TOLERANCE WITH HYSTRIX

## What are Microservices?
A service can be defined as a microservice if it has the following three things
* __The service is Exposed by REST__
* __Small well chosen Deployable Units__
* __Cloud Enabled__

## Challenges with building Microservices
| Challenges                        |   Solution                                                     |
| --------------------------------- |--------------------------------------------------------------  |
| 1.  BOUNDED CONTEXT               | Evolutionary. This is something that comes with __EXPERIENCE__ |
| 2.  CONFIGURATION MANAGEMENT      | __Spring Cloud Config Server__                                 |
| 3.  DYNAMIC SCALE UP/DOWN         | __Eureka, Ribbon Load Balancing, Feign__                       |
| 4.  VISIBILITY                    | __Zipkin Distributed Tracing, Netflix API Gateway__            |  
| 5.  FAULT TOLERENCE               | __Hystrix__                                                    |

## Advantages of Microservice Architecture
* New Technology and Process adaption
* Dynamic Scaling
* Faster Release Cycles

## Standardizing Ports and URLs
### Ports
| Application                      | Port               |       
| --------------------------------- | :----------------: |
| Limits Service                    | 8080, 8081 ......  |
| Spring Cloud Congif Server        | 8888               |
|                                   |                    |
| Currency Exchange Service         | 8000, 8001, 8002.. |
| Currency Conversion Service       | 8100, 8101, 8102.. |
| Netflix Eureka Naming Server      | 8761               |
| Netflix Zuul Api Gateway Server   | 8765               |
| Zipkin Distributed Tracing Server | 9411               | 

### URLs
| Application                      | URL                |       
| --------------------------------- | ---------------- |
| Limits Service                    | http://localhost:8080/limits POST -> http://localhost:8080/actuator/refresh  |
| Spring Cloud Config Server        | http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev               |
| Currency Converter Service - Direct Call        | http://localhost:8100/currency-converter/from/USD/to/INR/quantity/10 |
| Currency Converter Service - Feign       | http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000 |
| Currency Exchange Service    | http://localhost:8000/currency-exchange/from/EUR/to/INR http://localhost:8001/currency-exchange/from/USD/to/INR              |
| Eureka  | http://localhost:8761/            |
| Zuul - Currency Exchange & Exchange Services | http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10              | 
|Zipkin|  	http://localhost:9411/zipkin/ |
|Spring Cloud Bus Refresh | http://localhost:8080/bus/refresh |
