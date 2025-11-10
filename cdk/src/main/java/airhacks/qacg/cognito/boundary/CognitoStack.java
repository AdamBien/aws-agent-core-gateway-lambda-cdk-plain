package airhacks.qacg.cognito.boundary;

import airhacks.ConventionalDefaults;
import airhacks.qacg.cognito.control.UserPools;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class CognitoStack extends Stack {

    final String userPoolId;
    final String userPoolClientId;

    public CognitoStack(Construct scope, String appName, StackProps stackProps) {
        super(scope, ConventionalDefaults.stackName(appName, "cognito"), stackProps);

        var userPool = UserPools.create(this);
        var userPoolClient = UserPools.createClient(this, userPool);

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
    }

    public String userPoolId() {
        return userPoolId;
    }

    public String userPoolClientId() {
        return userPoolClientId;
    }

}
