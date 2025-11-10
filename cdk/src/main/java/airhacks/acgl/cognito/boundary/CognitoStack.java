package airhacks.acgl.cognito.boundary;

import airhacks.ConventionalDefaults;
import airhacks.acgl.cognito.control.UserPools;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.cognito.SignInUrlOptions;
import software.constructs.Construct;

public class CognitoStack extends Stack {

    final String userPoolId;
    final String userPoolClientId;

    public CognitoStack(Construct scope, String appName, StackProps stackProps) {
        super(scope, ConventionalDefaults.stackName(appName, "cognito"), stackProps);

        var userPool = UserPools.create(this);
        var userPoolClient = UserPools.createClient(this, userPool);
        var userPoolDomain = UserPools.createUserPoolDomain(this, userPool, appName);

        this.userPoolId = userPool.getUserPoolId();
        this.userPoolClientId = userPoolClient.getUserPoolClientId();

        CfnOutput.Builder.create(this, "UserPoolId")
                .value(userPoolId)
                .description("Cognito User Pool ID")
                .build();

        CfnOutput.Builder.create(this, "UserPoolClientId")
                .value(userPoolClientId)
                .description("Cognito User Pool Client ID")
                .build();

        CfnOutput.Builder.create(this, "UserPoolArn")
                .value(userPool.getUserPoolArn())
                .description("Cognito User Pool ARN")
                .build();

        CfnOutput.Builder.create(this, "UserPoolDomain")
                .value(userPoolDomain.baseUrl())
                .description("Cognito User Pool Domain")
                .build();

        CfnOutput.Builder.create(this, "UserSignUpURL")
                .value(userPoolDomain.signInUrl(userPoolClient, SignInUrlOptions.builder().redirectUri("https://example.com").build()))
                .description("User signup URL")
                .build();

    }

    public String userPoolId() {
        return userPoolId;
    }

    public String userPoolClientId() {
        return userPoolClientId;
    }

}
