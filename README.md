# AWS Agent Core Gateway with Lambda

AWS CDK-based infrastructure provisioning AWS Bedrock Agent Core Gateway integrated with AWS Lambda and Amazon Cognito authentication.

## Architecture

- **Lambda Function**: Event-driven handler processing incoming AWS service events
- **Agent Core Gateway**: Bedrock Agent Core Gateway with Cognito-based authentication
- **Cognito User Pool**: Dedicated authentication stack for API access control

## Modules

- [cdk](cdk/) - AWS CDK infrastructure definitions
- [lambda](lambda/) - Lambda function implementation

## Deployment

```bash
./buildAndDeployDontAsk.sh
```

