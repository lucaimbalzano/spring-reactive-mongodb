# spring-reactive-mongodb
                Build Reactive Rest APIs with Spring WebFlux and Reactive MongoDB



## Steps to Setup

**1. Clone Repository**

```bash
git clone https://github.com/callicoder/spring-webflux-reactive-rest-api-demo.git
```

**2. Build and run the app using maven**

```bash
cd spring-reactive-mongodb$
mvn clean install -i
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The server will start at <http://localhost:8080>.

## Exploring the Rest APIs

The application defines following REST APIs

```
 GET http://localhost:8090/api/person/allpeople - Get All people
```
```
POST http://localhost:8090/api/person/addperson - Create a new person

    example JSON:
                {
                    "firstname":"Mark",
                    "username": "Pistacchi",
                    "birth":"2022-02-11",
                    "age":34,
                    "addressDTO":    {
                        "id": "620683b6798d2c8021d4f207",
                        "street": "Cordelia",
                        "streetNumber": "250",
                        "postalCode": 2341,
                        "city": "Guchang",
                        "countryCode": "CN"
                    }
                }
```
```
PUT http://localhost:8090/api/person/updateperson/{id} - Update a person
```
```
DELETE http://localhost:8090/api/person/deleteperson/{id} - Delete a person
```

```
GET http://localhost:8090/api/address/getaddress/{id} - Retrieve an address by Id
```
```
GET http://localhost:8090/api/person/initDatabase - Initialize Database
```

## MockData
You can use those [Addresses](src/main/java/com/javaexample/spring/reactive/mockdata/Address.json) to add some data on your DB in MongoDB.


##### Requirements

Java - 11; 
 Maven - 3.x.x; 
 MongoDB - 3.x.x

## Running integration tests

The project also contains integration tests for all the Rest APIs. 
For running the integration tests, go to the root directory of the project and type `mvn test` in your terminal.
