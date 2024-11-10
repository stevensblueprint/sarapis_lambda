# Sarapis Lambda Functions
This repository contains the AWS Lambda functions to support CRUD operations for HSDS organization datasets. The Lambda
functions are vended behind an API and are listening to APIGatewayEvents.

## Invoke Lambda locally
Install sam (see [docs](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/install-sam-cli.html#install-sam-cli-instructions)).

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

## Start Development Server
You start an http server to invoke the Lambda function. If you look at the
`template.yaml` file, you will find the following definition:
```yaml
Events:
Api:
  Type: Api
  Properties:
    Path: /organizations
    Method: GET
```
This will publish a GET HTTP endpoint on `http://127.0.0.1:3000/organizations` that will invoke the lambda.
If you want to test any other endpoint you will need to change the `Path` and `Method` property in the `template.yaml` file.

You can start the development server running
```bash
cd function/organization-handler-lambda
sam build
sam local start-api
```
Any changes made to the Lambda function will be reflected automatically without having to run
`sam local start-api`. However, if you make any changes to the `template.yaml` file you will need
to run `sam build`.

## Test with locally running DynamoDB
Spin up the docker container with DynamoDB
```bash
docker-compose up -d
```
This will create a container with DynamoDB. The database can be accessed in port 8080.

## Formatting
We will be following the [Google Java Style](https://google.github.io/styleguide/javaguide.html) Guide.
To format your code before pushing you need to run the formatter:
```bash
cd function/organization-handler-lambda
mvn formatter:format
```
