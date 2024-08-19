# Kbnb 

In this assignment you'll be working on a small property booking site. On this site users can filter and search for available properties, view property details and submit a request to book a property.

### Viewing data 

this assignment uses H2 a in memory database that comes with a built in data viewer. To access the dataviewer run the application and navigate to `http://localhost:8080/h2-ui/` and in the `JDBC URL` section enter `jdbc:h2:mem:testdb`. Then for username and password enter `sa` and leave the password empty

in the dataviewer use sql syntax to query data

### IDE 

For the IDE of use intelij is recommended whether its the community edition of ultimate either will work

### Running the application 

To run the application you can use gradle. To use gradle navigate to the `tasks`->`application`->`bootRun` and click on it

you can also go to the `KbnbApplication.java` right click on it and on the context menu run the main class of the file

Make sure that you have nothing running on port 8080 if you have anything running on port 8080 in the `application.properties` file add `server.port=<portNum>` 

## Tasks


### Get property by Name
In this task we are going to add an endpoint that will get all the properties that contain the query parameter `name` edit the `getPropertyByName` method in the `PropertyControllerV1.java` file

edit the `PropertyControllerV1.java`, `PropertyService.java` and `PropertyRepo.java` file as needed to complete the task


### Delete a property
In this task you will delete a property from the database

in the `PropertyControllerV1.java` class you will edit the `deleteProperty` method to achieve this. Add a request path mapping to get the id that we are going to delete

after the property is deleted return a `204` Response code and no body

edit the `PropertyControllerV1.java`, `PropertyService.java` and `PropertyRepo.java` file as needed to complete the task


### Fix Endpoint
In this task you will fix a endpoint that has does not satisfy the requirements

the `/api/property/minstars` endpoint should accept both `name`(String) and `stars`(double) query parameters and should return a list of properties that contain the `name` query param and has at least the amount of stars as the `stars` query param 

The query used to retrieve the data should be parameterized for security purposes  

Navigate to `getPropertiesWithMinimumStars` 


### Add unit tests for Property Service
Add a unit tests to the `PropertyServiceTest.java` class for the `getFakeProperty` method
- 1 unit test named `Fake Property Happy Path` for the happy path of returning a fake property 
- 1 unit test named `Restricted Name Should Throw Error` for when an error should be thrown if the property name is `"restricted"`
- 1 unit test named `Response Includes Should Put In a Real Name` that verifies if the input name is `"name"` it will return a property that has a name of `Put in a real name`


### Get Super hosts

A host is considered a "Super Host" if they have an average star rating >= 4 across all of the properties they own. Each property has a hostId field you can use to find all the properties owned by a certain host. For example if you had the following properties `{hostId: 1, stars: 3, ...},  {hostId: 2, stars: 5, ...},  {hostId: 2, stars: 3, ...},]` All of Host 2's properties should be displayed when the Super Host filter is on because they have an average rating of 4 stars

In this task edit the `getSuperHosts` method to return a list of ids that has all the host ids that are super hosts for all the properties

edit the `PropertyControllerV1.java`, `PropertyService.java` and `PropertyRepo.java` file as needed to complete the task


### Property Lookup Tree

In this task you are going to be constructing a Lookup tree of all the properties
This function receives the search text entered by the user, and an object that represents a tree containing each property grouped by the letters in its name. For example if there were 3 properties named "B", "Bar" and "Baz" then propertyLookupTree would be:
```json
{
  b: {
    match: {id: "b", name: "B"}
    a: {
      r: {
        match: {id: "bar", name: "Bar"}
      },
      z: {
        match: {id: "baz", name: "Baz"}
      }
    }
  }
}
```
In this task use the `LookUpTreeNode.java` and `LookUpMatch.java` classes to create the tree and edit the `PropertyControllerV1.java`, `PropertyService.java` and `PropertyRepo.java` file as needed to complete the task 

In the response make sure the Json returned does not include null values


### Request Validation 

in this task you will be validating incoming request objects. 

Add the `Spring Boot Starter Validation` dependency in `build.gradle` and add the `@Valid` notation in the `addProperty` method in the `PropertyControllerV1` class. 

Modify the `PropertyRequestBody.java` class to make sure the name is not null and rate is greater than 100

Add Exception handling to the controller send back a response with a status code of 400 and a message with the text `"Request body invalid"`


### Add Reservation

Add a `Reservation` Entity with properties `reservationId`, `reservationDate`, `numberOfGuests` and `reservationProperty`(Proeprty being a one-to-many relation)

create a controller that has a controller mapping of `api/reservation` and a Post Mapping of (`/add`) request body should look like the following
```json
{
  "reservationDate": "2024-09-09",
  "numberOfGuests": "2",
  "reservationPropertyId": 1
}
```
After the reservation has been added return the Entity created with a Http Status code of `201`

Add a check to the endpoint where if the reservation already exists for a property then we want to return a `400` response code and add a message to the response `Reservation date unavailable`

