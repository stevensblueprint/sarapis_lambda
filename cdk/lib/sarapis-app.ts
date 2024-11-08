import * as cdk from 'aws-cdk-lib';
import { OrganizationStack } from "./organization-cdk";

const app = new cdk.App();
new OrganizationStack(app, 'OrganizationLambdaStack');
app.synth();
