#SETUP
* Spring boot application
* Wiremock server, mocking third party APIs
* Redis instance <br />

To Launch the application, navigate to the root folder and run

```docker-compose up```

It will launch a Redis instance, on port 6380, and a wiremock server on port 8889

Run spring boot

```mvn package```

```mvn spring-boot:run```

Spring boot will run on port 8081

#Caching demo
* Open postman
* Call localhost:8081/person-details?id=1, with method GET. it will take at least 500 ms
* Call the API again immediately after, the call will take significantly less time, < 10 ms depending on system specifications
* Wait 30 seconds, and call the API again, it will take > 500 ms. This is because the entry got evicted  


# Explanation
* The service layer for /person-details calls a third party API, localhost:8889/details to get the required information
* localhost:8889/details is configured in Wiremock to respond with a delay of 500 ms. Refer to /resources/wiremock/mappings/person-details-success.json
* ApiGatewayServiceImpl has the functions that call third party APIs. During the first call, the response is cached in Redis, using RedisService, with a TTL of 30 seconds
* During the second call, the function finds the entry in Redis, and returns it immediately.
* After 30 seconds pass, Redis will evict the entry, and the service layer has to call the external API again

You can also observe the above using a Redis GUI, such as RedisInsights.
