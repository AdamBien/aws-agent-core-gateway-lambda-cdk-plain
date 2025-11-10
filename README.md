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

### Cognito Hosted UI

Access the Cognito login page (available in CDK outputs as `<domainPrefix>-domain-url`):
```
https://<domain-prefix>.auth.<region>.amazoncognito.com/login?client_id=<client-id>&response_type=token&redirect_uri=<redirect-uri>
```
