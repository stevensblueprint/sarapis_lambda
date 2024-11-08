# Sarapis Lambda Functions
This repository contains the AWS Lambda functions to support CRUD operations for HSDS organization datasets. The Lambda
functions are vended behind an API and are listening to APIGatewayEvents.

## Invoke Lambda locally
Install sam (See [docs](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/install-sam-cli.html#install-sam-cli-instructions)).

Move to the `organization-handler-lambda`
```bash
cd function/organization-handler-lambda
```
Build the lambda
```bash
sam build
```
Invoke the lambda
```bash
sam local invoke OrganizationLambdaTest --event event.json
```

## Test with locally running DynamoDB
Spin up the docker container with DynamoDB
```bash
docker-compose up -d
```
This will create a container with DynamoDB. The database can be accessed in port 8080.
