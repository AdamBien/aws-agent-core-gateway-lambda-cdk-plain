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

To Access the signup/login page use the value of the output: `agent-core-gateway-lambda-cognito-stack.UserSignUpURL`

The Hosted UI handles OAuth implicit grant flow and returns the JWT token in the URL fragment after authentication.



### CLI-Based Authentication

Obtain JWT token programmatically:
```bash
aws cognito-idp initiate-auth \
  --auth-flow USER_PASSWORD_AUTH \
  --client-id <client-id> \
  --auth-parameters USERNAME=<email>,PASSWORD=<password> \
  --query 'AuthenticationResult.IdToken' \
  --output text
```

### Calling the MCP Server

Invoke the Agent Core Gateway with the bearer token:

```bash
TOKEN="<your-jwt-token>"
GATEWAY_URL="<agent-core-gateway-url>"

curl -X POST "${GATEWAY_URL}" \
  -H "Authorization: Bearer ${TOKEN}" \
  -H "Content-Type: application/json" \
  -d '{
    "your": "payload"
  }'
```

The gateway authenticates requests using the Cognito JWT token and forwards events to the Lambda function for processing.