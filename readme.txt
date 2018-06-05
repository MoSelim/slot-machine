To build the solution:
mvn package

I used in memory h2 database you can find credentials and url in application.properties
;
the target jar can be fount after mvn comman under the target folder slot-machine-0.0.1.jar

I executed a seeding method in the App class to sedd the database for the application to run.
There is a default created user Mohamed Selim with id 1

services:
localhost:8080/account/balance/1 GET
localhost:8080/account/play/1 POST
localhost:8080/account/deposit/1 POST with amount in body
localhost:8080/account/create POST with account object



