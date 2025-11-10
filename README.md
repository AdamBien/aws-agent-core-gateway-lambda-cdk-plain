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

## Testing

After deployment, the stack outputs provide the necessary endpoints and identifiers:

1. **Create Cognito User**: Use AWS Console or CLI to create a user in the Cognito User Pool
2. **Authenticate**: Obtain JWT token via Cognito USER_PASSWORD_AUTH flow using the User Pool Client ID
3. **Invoke Gateway**: Call the Agent Core Gateway URL with the JWT token in the Authorization header

The Lambda function processes incoming events and returns responses through the Bedrock Agent Core Gateway with Cognito authorization.

