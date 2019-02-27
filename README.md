# idempiere-micro-liberty-standalone

[![CircleCI](https://circleci.com/gh/iDempiere-micro/idempiere-micro-liberty-standalone.svg?style=svg)](https://circleci.com/gh/iDempiere-micro/idempiere-micro-liberty-standalone)
[![Maintainability](https://api.codeclimate.com/v1/badges/a84d5e3a20200ae58226/maintainability)](https://codeclimate.com/github/iDempiere-micro/idempiere-micro-liberty-standalone/maintainability)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8655982e68454ce4bc5f1742a93f528b)](https://www.codacy.com/app/davidpodhola/idempiere-micro-liberty-standalone?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=iDempiere-micro/idempiere-micro-liberty-standalone&amp;utm_campaign=Badge_Grade)

The standalone all-in-one iDempiere-micro microservice containing all the modules from CRM to Invoicing.

## Build

Have [iDempiere-micro PosgreSQL Docker 6.2](https://github.com/iDempiere-micro/idempiere-docker-pgsql-6.1/tree/6.2) running on port 5433 to have the tests succeed.

`IDEMPIERE_MICRO_VERSION=0.10.0 mvn clean install -P minify-runnable-package`

## Run

### Standalone

#### Prepare the configuration
`sudo cp ./src/main/resources/defaults.properties /etc/idempiere-micro.properties`

Then edit `/etc/idempiere-micro.properties` and setup accordingly.

#### Start the microservice
`(cd target; java -jar idempiere-micro-liberty-standalone.jar)`

### Using [OpenLiberty](https://openliberty.io/)
`mvn liberty:run-server`

