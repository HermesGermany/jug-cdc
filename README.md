# jug-cdc
Consumer-driven contracts demo repository

### Start Pact Broker
`docker-compose -f docker-compose.yml up`

 visit pact broker at [http://localhost](http://localhost)
 
### Create Consumer Pact and publish to broker
`mvn clean test pact:publish -Dpact.consumer.version=2.2.3 -Dpact.tag=dev -f pact-consumer/pom.xml`

### Validate Provider against broker
`mvn clean test -Dpact.provider.version=1.2.3 -f pact-provider/pom.xml`
