# Webflux

* Docker
* Java 11

This application is meant to show the differences between blocking external calls and reactive nonblocking external calls.
 

# Running Locally

```bash
./mvnw spring-boot:run

```

 

## Endpoints

### Blocking

Endpoint that will call an external service in a blocking fashion.

```
http://localhost:8080/blocking

```

### Nonblocking

Endpoint that will call an external service in a nonblocking fashion.

```
http://localhost:8080/nonblocking

```

When under load this endpoint will better utilize the threads and be able to maintain response times, whereas the blocking response times will begin to increase.

## Dependencies

There needs to be another service running at `http:localhost:8081/` that preferably has a slow 5 seconds response time.

# Running Load Test

Make sure this application is running at `http://localhost8080` and the external dependency is running at `http://localhost:8081/`.


Then run the following command which will kick off a k6 load test.

```
./run-load-test.sh

```


To run test against `/nonblocking` change `script.js` from

```javascript
get({path: 'blocking'})

```
to
```javascript
get({path: 'nonblocking'})

```

 

## Load Test Results

Example of `/blocking` endpoint. Notice the p(95) which means 95% of the traffic returned within that time.
```
✗ http_req_duration..........: avg=10.44s   min=5.01s   med=11.99s max=13.35s  p(90)=13.01s   p(95)=13.15s

```

Example of `/nonblocking` endpoint. Notice the p(95) which means 95% of the traffic returned within that time.

At just 5 seconds it shows that it was able to process its requests without blocking and stacking up threads.
5s is good in this example as 5s is the response time for the slow external service running at `http://localhost:8081/`.
```
✓ http_req_duration..........: avg=5s       min=4.97s  med=5.01s   max=5.01s   p(90)=5.01s    p(95)=5.01s

```