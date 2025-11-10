package airhacks.qacg.cognito.control;

import software.amazon.awscdk.services.cognito.UserPool;
import software.amazon.awscdk.services.cognito.UserPoolClient;
import software.amazon.awscdk.services.cognito.OAuthFlows;
import software.amazon.awscdk.services.cognito.OAuthSettings;
import software.constructs.Construct;

public interface UserPools {

    static UserPool create(Construct scope) {
        return UserPool.Builder.create(scope, "UserPool")
                .userPoolName("agent-core-gateway-users")
                .selfSignUpEnabled(true)
                .signInAliases(software.amazon.awscdk.services.cognito.SignInAliases.builder()
                        .email(true)
                        .build())
                .build();
    }

    static UserPoolClient createClient(Construct scope, UserPool userPool) {
        return UserPoolClient.Builder.create(scope, "UserPoolClient")
                .userPoolClientName("agent-core-gateway-client")
                .userPool(userPool)
                .generateSecret(false)
                .authFlows(software.amazon.awscdk.services.cognito.AuthFlow.builder()
                        .userPassword(true)
                        .userSrp(true)
                        .build())
                .oAuth(OAuthSettings.builder()
                        .flows(OAuthFlows.builder()
                                .implicitCodeGrant(true)
                                .build())
                        .build())
                .build();
    }

}
