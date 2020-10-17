# Problem Statement

Design a cab booking system with the below requirements

Details
--------

1. The location is represented as (x,y) coordinates
2. Distance between the two points is cartesian distance
3. Maximum distance a driver has to travels to pickup
4. A can has only one drive
5. Sharing of cab service is not allowed
6. There is a single type of cab

Swagger 
-------

http://localhost:8080/swagger-ui/index.html#/
https://dzone.com/articles/spring-boot-restful-api-documentation-with-swagger

Functionalities
----------------

Register a rider -- tested

Register a driver/cab -- tested

Update cab Location -- tested

Driver can switch of his services -- tested 

A rider can book a cab -- tested

Fetch history of the rides takes by a rider -- tested

End the trip -- tested

Testing Flow
------------

{{CAB}}

1. Register Cab
{
    URL : /register/cab
    MET : POST
    REQ : {
              "id" : "5",
              "driverName" : "Chumpli"
          }
}

2. Update Cab Location
{
    URL : /update/location/cab/{cabId}
    MET : POST
    REQ : {
              "x" : "12.2",
              "y" : "92.1"
          }
}

3. Update Cab Availability
{
    URL : /update/cab/{cabId}/availability/{availability}
    MET : POST
    REQ : {}
}

4. End the trip
{
    URL : /update/cab/{cabId}/end/trip
    MET : POST
    REQ : {}
}

5. Show all the cabs registered with the app
{
    URL : /cabs/all
    MET : GET
    REQ : {}
}


{{RIDER}}

1. Register a rider
{
    URL : /register/rider
    MET : POST
    REQ : 
}

2. Book a cab
{}

3. Fetch the ride history
{}

