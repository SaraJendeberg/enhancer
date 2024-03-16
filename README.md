How to trigger the data enhancement pipeline.

Start the springboot service using the following command
```
./mvnw spring-boot:run
```

Open a new terminal and trigger the pipeline with 
```
curl -X POST http://localhost:8080/pipeline/start
```

Follow the logging of the pipeline in the first terminal running the service. 