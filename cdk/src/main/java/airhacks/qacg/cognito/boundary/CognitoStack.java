package airhacks.qacg.cognito.boundary;

import airhacks.ConventionalDefaults;
import airhacks.qacg.cognito.control.UserPools;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class CognitoStack extends Stack {

    public CognitoStack(Construct scope, String appName, StackProps stackProps) {
        super(scope, ConventionalDefaults.stackName(appName, "cognito"), stackProps);

        var userPool = UserPools.create(this);
        var userPoolClient = UserPools.createClient(this, userPool);

        CfnOutput.Builder.create(this, "UserPoolId")
                .value(userPool.getUserPoolId())
                .description("Cognito User Pool ID")
                .exportName(appName + "-UserPoolId")
                .build();

        CfnOutput.Builder.create(this, "UserPoolClientId")
                .value(userPoolClient.getUserPoolClientId())
                .description("Cognito User Pool Client ID")
                .exportName(appName + "-UserPoolClientId")
                .build();

        CfnOutput.Builder.create(this, "UserPoolArn")
                .value(userPool.getUserPoolArn())
                .description("Cognito User Pool ARN")
                .exportName(appName + "-UserPoolArn")
                .build();
    }

}
