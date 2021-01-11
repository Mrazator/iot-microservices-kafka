# Microservices project for PV217

Microservices prove of concept project for course PV217.  It provides functionality for collecting, analyzing and persisting IOT data, using event streams (kafka) 

## Logical architecture

![Logical architecture](./architecture/logical-architecture.drawio.png)

## Generator service
  - periodically generates measurements related to power transmission devices
  - push generated data to kafka
  - Features:
    * implements async messaging since using kafka
  - expected output example:
  ```json
  {
	"controlCenterId": "CC-BORDER-CZ/SK",
	"measurements": [
		{
			"name": "freq",
			"timestamp": 1608385521010,
			"value": 42.15563
		},
		{
			"name": "MW",
			"timestamp": 1608385521010,
			"value": -8.813544
		},
		{
			"name": "MVAR",
			"timestamp": 1608385521010,
			"value": -8.813544
		},
		{
			"name": "TemperFahr",
			"timestamp": 1608385521010,
			"value": 66.118645
		}
	]
}
  ```

## Collector service
  - subscribed to kafka, listening for data from generator
  - do some processing (remove unneeded properties, converts some measurements to other units, etc...)
  - push data to kafka
  - Features:
    * implements feature of horizontal scaling since multiple instances help better performance
    * implements async messaging since using kafka
  - expected output example:
  ```json
  {
	"controlCenterId": "CC-BORDER-CZ/SK",
	"measurements": [
		{
			"name": "Frequency",
			"measuredAt": "2020-12-22T08:40:01.005Z",
			"collectedAt": "2020-12-22T08:40:02.500Z",
			"value": 67.193
		},
		{
			"name": "Active Power",
			"measuredAt": "2020-12-22T08:40:01.010Z",
			"collectedAt": "2020-12-22T08:40:02.505Z",
			"value": 123.71
		},
		{
			"name": "Temperature",
			"measuredAt": "2020-12-22T08:40:01.015Z",
			"collectedAt": "2020-12-22T08:40:02.510Z",
			"value": 24.362
		}
	]
  }
  ```

## Archiver service
  - subscribed to kafka, listening for collected data
  - uses DB to persist collected data
  - provides REST API for retrieving specified data in specified time frames
  - Features: 
    * implements feature simulating db unavailability (throwing error at random)
    * implements async messaging since using kafka

## Analyzer service
  - provides REST API for retrieving statistical data about measurements per time frame and different intervals (for example, avg values per hours, during last day, etc...)
  - uses Archiver REST API to retrieve needed data
  - Features:
    * implements feature of "self-healing" by retrying data retrieving from archiver
  - expected request example:
  - expected output example (for request ***{baseurl}/statistics?name=Active%20Power&unit=MINUTES***)
  ```json
  [
	{
		"average": 247.773,
		"count": 72,
		"intervalEnd": "2021-01-11T17:51:00Z",
		"intervalStart": "2021-01-11T17:50:00Z",
		"name": "Active Power"
	},
	{
		"average": 259.823,
		"count": 119,
		"intervalEnd": "2021-01-11T17:52:00Z",
		"intervalStart": "2021-01-11T17:51:00Z",
		"name": "Active Power"
	},
	{
		"average": 244.275,
		"count": 119,
		"intervalEnd": "2021-01-11T17:53:00Z",
		"intervalStart": "2021-01-11T17:52:00Z",
		"name": "Active Power"
	},
	{
		"average": 250.972,
		"count": 20,
		"intervalEnd": "2021-01-11T17:54:00Z",
		"intervalStart": "2021-01-11T17:53:00Z",
		"name": "Active Power"
	}
  ]
  ```

## Notification service
  - used as kind of logging system
  - subscribed to all kafka topics and stores activity history