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

## Try with cURL
_Note instead of cURL you can also install [Postman](https://www.getpostman.com/) and 
import  [the iDempiere micro Postman collection](https://github.com/iDempiere-micro/idempiere-micro-liberty-standalone/blob/master/tools/idempiere-micro-liberty-standalone.postman_collection.json)_.

### Prerequisities

You need [jq](https://stedolan.github.io/jq/) to parse the JSON returned by login.

### Authenticate
#### Get the JWT token for _GardenUser_

`token=$( curl http://localhost:9080/idempiere-micro-liberty-standalone/session/GardenUser/login/GardenUser | jq -r ".token")
`
### Query
#### Get the current user

`curl -H "Content-Type: application/graphql" -H "Authorization: Bearer $token" --request POST --data 'query { me { id description } }' http://localhost:9080/idempiere-micro-liberty-standalone/graphql`

#### Get the list of the users

`curl -H "Content-Type: application/graphql" -H "Authorization: Bearer $token" --request POST --data 'query { users { id description } }' http://localhost:9080/idempiere-micro-liberty-standalone/graphql`

#### Get the list of the business partners

`curl -H "Content-Type: application/graphql" -H "Authorization: Bearer $token" --request POST --data 'query { businessPartners { id name searchKey } }' http://localhost:9080/idempiere-micro-liberty-standalone/graphql`

### Mutate
#### Create a business partners

`curl -H "Content-Type: application/graphql" -H "Authorization: Bearer $token" --request POST --data 'mutation { createBusinessPartner(name: "TEST1", searchKey: "TEST1") { id name } }' http://localhost:9080/idempiere-micro-liberty-standalone/graphql`