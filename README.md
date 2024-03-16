How to trigger the data enhancement pipeline.

Start the springboot service using the following command
```
git clone git@github.com:SaraJendeberg/enhancer.git
cd enhancer
./mvnw spring-boot:run
```

Open a new terminal and trigger the pipeline with 
```
curl -X POST http://localhost:8080/pipeline/start
```

Follow the logging of the pipeline in the first terminal running the service. 