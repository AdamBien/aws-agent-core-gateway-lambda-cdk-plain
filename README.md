# AWS Agent Core Gateway with Lambda

AWS CDK-based infrastructure provisioning AWS Bedrock Agent Core Gateway integrated with AWS Lambda and Amazon Cognito authentication.

## Architecture

- **Lambda Function**: Event-driven handler processing incoming AWS service events and returning personalized greetings
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
  --query 'AuthenticationResult.AccessToken' \
  --output text
```

### Calling the MCP Server

Invoke the Agent Core Gateway with the bearer token:

```bash
./callMCPServer.sh <gateway-url> <jwt-token>
```

Or with a custom payload file:
```bash
./callMCPServer.sh <gateway-url> <jwt-token> payload.json
```

The gateway validates the token and forwards events to the Lambda function for processing.

### Using MCP Inspector

Test the endpoint interactively with the Model Context Protocol inspector:

```bash
npx @modelcontextprotocol/inspector <gateway-url>
```

The inspector provides a web interface for exploring available tools, sending requests, and inspecting responses. Authentication tokens can be configured in the inspector's settings.