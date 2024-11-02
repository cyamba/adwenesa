# Welcome to YambaCode LLM-Client

Welcome to the **YambaCode LLM-Client**! 

This prototype will be part of the YambaCode "Adwene Bone" projects.

As a POC, it will simple and explorative in nature. 

(The project name "Adwene Bone" is Akan for "Artificial Intelligence") 

This project serves as a client for interacting with language models in a structured and efficient way. 

Verify the configuration details, such as the server port, in [`application.yaml`](application.yaml) to ensure they meet your requirements.


Swagger version:
```shell
open http://localhost:3141/v3/api-docs
```
Swagger ui:
```shell
open http://localhost:3141/swagger-ui/index.html
```
Verify the port is correct in [`application.yaml`](src/main/resources/application.yaml).

To verify meta-data application status with actuator:
```shell
open http://localhost:3141/actuator
```
health and info should be exposed:
```shell
open http://localhost:3141/actuator/health
open http://localhost:3141/actuator/info
```


