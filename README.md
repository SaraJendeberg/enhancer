How to trigger the data enhancement pipeline.
```
git clone git@github.com:SaraJendeberg/enhancer.git
cd enhancer
```

Due to github storage being limited, download the data from 
```
https://console.cloud.google.com/storage/browser/motherbrain-external-test
```
Move the files to the folder '/enhancer/src/main/resources/' and decompress them with
```
gzip -d src/main/resources/interview-test-funding-2019.json.gz
gzip -d src/main/resources/interview-test-org-2019.json.gz
```

Start the springboot service using the following commands
```
./mvnw spring-boot:run
```

Open a new terminal and trigger the pipeline with 
```
curl -X POST http://localhost:8080/pipeline/start
```
Follow the logging of the pipeline in the first terminal running the Springboot service. 

After the pipeline is finished, the output can be found in
```
company-data-output.json
``` 

Optionally, you can compress the enhanced data using
```
gzip -k company-data-output.json
```
to save storage. 
