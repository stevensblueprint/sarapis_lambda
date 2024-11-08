import * as cdk from 'aws-cdk-lib';
import {Duration, RemovalPolicy} from 'aws-cdk-lib';
import {ManagedPolicy, Role, ServicePrincipal} from 'aws-cdk-lib/aws-iam';
import {Code, Function, Runtime} from 'aws-cdk-lib/aws-lambda';
import {LogGroup, RetentionDays} from 'aws-cdk-lib/aws-logs';
import {Construct} from 'constructs';
import {AttributeType, BillingMode, Table} from 'aws-cdk-lib/aws-dynamodb';
import {HttpApi, HttpMethod, PayloadFormatVersion} from 'aws-cdk-lib/aws-apigatewayv2';
import {HttpLambdaIntegration} from 'aws-cdk-lib/aws-apigatewayv2-integrations';

export class OrganizationStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);
    const lambdaExecutionRole = new Role(this, `${id}-OrganizationHandlerLambdaRole`, {
      assumedBy: new ServicePrincipal('lambda.amazonaws.com'),
      roleName: `${id}-OrganizationHandlerLambdaRole`
    });
    lambdaExecutionRole.addManagedPolicy(
        ManagedPolicy.fromAwsManagedPolicyName('service-role/AWSLambdaBasicExecutionRole')
    );

    const organizationHandlerLambdaFunctionName = `${id}-OrganizationHandlerLambda`;

    const organizationHandlerLambda = new Function(this, organizationHandlerLambdaFunctionName, {
      runtime: Runtime.JAVA_21,
      code: Code.fromAsset('../function/organization-handler-lambda/target/organization-handler-lambda.jar'),
      handler: 'com.sarapis.lambda.OrganizationHandlerLambda::handleRequest',
      functionName: organizationHandlerLambdaFunctionName,
      role: lambdaExecutionRole,
      timeout: Duration.seconds(30),
      memorySize: 128,
      description: 'This Lambda function handles organization-related tasks',
      logGroup: new LogGroup(this, `${organizationHandlerLambdaFunctionName}-logGroup`, {
        logGroupName: `aws/lambda/${organizationHandlerLambdaFunctionName}-logGroup`,
        retention: RetentionDays.ONE_WEEK,
        removalPolicy: RemovalPolicy.DESTROY
      })
    });

    const organizationDynamoDbTable = new Table(this, 'organizations-dynamodb-table', {
      tableName: 'organizations',
      partitionKey: {
        name: 'organization_id',
        type: AttributeType.STRING
      },
      billingMode: BillingMode.PROVISIONED,
      removalPolicy: RemovalPolicy.DESTROY
    });

    organizationDynamoDbTable.grantFullAccess(organizationHandlerLambda);

    const httpApi = new HttpApi(this, 'http-api-gateway', {
      apiName: `${id}-http-api-gateway`,
      description: 'HTTP API Gateway for Organization Lambda',
    });

    const organizationHandlerIntegration = new HttpLambdaIntegration('organization-handler-lambda-integration',
        organizationHandlerLambda, {
          payloadFormatVersion: PayloadFormatVersion.VERSION_1_0
        });


    httpApi.addRoutes({
      path: '/api/organizations',
      integration: organizationHandlerIntegration,
      methods: [HttpMethod.GET]
    });

    httpApi.addRoutes({
      path: '/api/organizations/{id}',
      integration: organizationHandlerIntegration,
      methods: [HttpMethod.GET]
    });

    httpApi.addRoutes({
      path: '/api/organizations',
      integration: organizationHandlerIntegration,
      methods: [HttpMethod.POST, HttpMethod.PUT]
    });

    httpApi.addRoutes({
      path: '/api/organizations/{id}',
      integration: organizationHandlerIntegration,
      methods: [HttpMethod.DELETE]
    });
  }
}
