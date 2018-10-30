# jug-cdc
Consumer-driven contracts demo repository

### Start Pact Broker
`docker-compose -f docker-compose.yml up`

 visit pact broker at [http://localhost](http://localhost)
 
### Create Consumer Pact and publish to broker
`mvn clean test pact:publish -f pact-consumer/pom.xml`

### Validate Provider against broker
`mvn clean test -f pact-provider/pom.xml`
