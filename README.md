## Microservices project for PV217
Microservices prove of concept project for course PV17.  It provides functionality for collecting, analyzing and persisting iot data, using event streams (kafka) 

#### Generator service
  - periodically enerates measurements related to power transmission devices
  - push generated data to kafka
  - Features:
    * implements async messaging since using kafka

#### Collector service
  - subscribed to kafka, listening for data from generator
  - do some processing (remove unneeded properties, converts some measurements to other units, etc...)
  - push data to kafka
  - Features:
    * implements feature of horizontal scaling since multiple instances help better performance
    * implements async messaging since using kafka

#### Archiver service
  - subscribed to kafka, listening for collected data
  - uses DB to persist collected data
  - provides REST API for retrieving specified data in specified time frames
  - Features: 
    * implements feature simulating db unavailability (throwing error at random)
    * implements async messaging since using kafka

#### Analyzer service
  - provides REST API for retrieving statistical data about measurements per time frame and different intervals (for example, avg values per hours, during last day, etc...)
  - uses Archiver REST API to retriev needed data
  - Features:
    * implements feature of "self-healing" by retrying data retrieving from archiver

#### Notification service
  - used as kind of logging system
  - subscribed to all kafka topics and stores activity history