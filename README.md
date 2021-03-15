# DWP Online Test

## Task

Using the language of your choice please build your own API which calls the API at https://bpdts-test-app.herokuapp.com/, and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London.

## Technical Description

* API developed in Java 8 using Spring Boot framework and Lombok plugin.
* Asynchronous programming implemented for calling services
* Exception Handling implemented including Custom Exception
* Unit test cases written using Junit

## To Run Locally
 
 * For running this application in an IDE like IntelliJ, Lombok plugin and Annotation Processors needs to be enabled in the IDE.
 
## Endpoints

### Fetches the instructions for this API :

Request URL 

`GET http://localhost:8081/api/v1/`

Response 

`Build an API which calls this API, and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London. Push the answer to Github, and send us a link.`


### Fetches all users :

Request URL 

`GET http://localhost:8081/api/v1/users`

### Fetches the details of a user when Id is provided :

Request URL 

`GET http://localhost:8081/api/v1/user/{id}`

`E.G. : http://localhost:8081/api/v1/user/1`

Response

`{"id":1,"first_name":"Maurise","last_name":"Shieldon","email":"mshieldon0@squidoo.com","ip_address":"192.57.232.111","latitude":34.003135,"longitude":-117.7228641}`


### Fetches the users in London and around London when distance is provided :

Request URL 

`GET http://localhost:8081/api/v1/users/{city}/{distance}`

`E.G. : http://localhost:8081/api/v1/users/London/50`

Response

`[{"id":135,"first_name":"Mechelle","last_name":"Boam","email":"mboam3q@thetimes.co.uk","ip_address":"113.71.242.187","latitude":-6.5115909,"longitude":105.652983,"residenceStatus":"LONDON"},{"id":396,"first_name":"Terry","last_name":"Stowgill","email":"tstowgillaz@webeden.co.uk","ip_address":"143.190.50.240","latitude":-6.7098551,"longitude":111.3479498,"residenceStatus":"LONDON"},{"id":520,"first_name":"Andrew","last_name":"Seabrocke","email":"aseabrockeef@indiegogo.com","ip_address":"28.146.197.176","latitude":27.69417,"longitude":109.73583,"residenceStatus":"LONDON"},{"id":658,"first_name":"Stephen","last_name":"Mapstone","email":"smapstonei9@bandcamp.com","ip_address":"187.79.141.124","latitude":-8.1844859,"longitude":113.6680747,"residenceStatus":"LONDON"},{"id":688,"first_name":"Tiffi","last_name":"Colbertson","email":"tcolbertsonj3@vimeo.com","ip_address":"141.49.93.0","latitude":37.13,"longitude":-84.08,"residenceStatus":"LONDON"},{"id":794,"first_name":"Katee","last_name":"Gopsall","email":"kgopsallm1@cam.ac.uk","ip_address":"203.138.133.164","latitude":5.7204203,"longitude":10.901604,"residenceStatus":"LONDON"},{"id":266,"first_name":"Ancell","last_name":"Garnsworthy","email":"agarnsworthy7d@seattletimes.com","ip_address":"67.4.69.137","latitude":51.6553959,"longitude":0.0572553,"residenceStatus":"NEAR_LONDON"},{"id":322,"first_name":"Hugo","last_name":"Lynd","email":"hlynd8x@merriam-webster.com","ip_address":"109.0.153.166","latitude":51.6710832,"longitude":0.8078532,"residenceStatus":"NEAR_LONDON"},{"id":554,"first_name":"Phyllys","last_name":"Hebbs","email":"phebbsfd@umn.edu","ip_address":"100.89.186.13","latitude":51.5489435,"longitude":0.3860497,"residenceStatus":"NEAR_LONDON"}]`