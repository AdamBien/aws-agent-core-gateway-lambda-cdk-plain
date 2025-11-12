# CDK Infrastructure

AWS CDK-based infrastructure provisioning AWS Bedrock Agent Core Gateway integrated with AWS Lambda and Amazon Cognito authentication.

## Stacks

- **CognitoStack** - User pool with app client for JWT-based authentication
- **AgentCoreGatewayStack** - Main stack containing Lambda function, Agent Core Gateway, and integration target

## Configuration

Region is configured via `ZCfg` from `stack.props.region` property.

## Business Components

- **cognito** - Cognito user pool configuration
- **gateway** - Agent Core Gateway and target setup
- **lambda** - Lambda function creation
- **configuration** - Stack configuration management

## Outputs

After deployment, the following CloudFormation outputs are available:

- `GatewayURL` - Agent Core Gateway endpoint URL
- `GatewayId` - Gateway identifier
- `UserSignUpURL` - Cognito hosted UI for user registration/login
