# How to trigger the pipelinee
```
git clone git@github.com:SaraJendeberg/enhancer.git
cd enhancer
```
Due to github storage being limited, the data files could not be included. Download the data from 
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
The progress of the pipeline can be viewed in the first terminal running the Springboot service. 

After the pipeline is finished, the output can be found in
```
company-data-output.json
``` 

Optionally, compress the enhanced data using
```
gzip -k company-data-output.json
```

### Future Work
- When reading 2019 data files, add in alphabetical order to enable binary search on company name.
- Stream data directly from GCS using com.google.cloud.google-cloud-storage.
- Export the company data to GCS using the same lib. 
