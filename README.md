# Sarapis Lambda Functions
This repository contains the AWS Lambda functions to support CRUD operations for HSDS organization datasets. The Lambda
functions are vended behind an API and are listening to APIGatewayEvents.

## Test with locally running DynamoDB
Spin up the docker container with DynamoDB
```bash
docker-compose up -d
```
This will create a container with DynamoDB. The database can be accessed in port 8080.