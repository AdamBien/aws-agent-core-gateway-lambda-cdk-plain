package airhacks.acgl.cognito.control;

import software.amazon.awscdk.services.cognito.UserPool;
import software.amazon.awscdk.services.cognito.UserPoolClient;
import software.amazon.awscdk.services.cognito.UserPoolDomain;
import software.amazon.awscdk.services.cognito.UserVerificationConfig;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.services.cognito.AuthFlow;
import software.amazon.awscdk.services.cognito.AutoVerifiedAttrs;
import software.amazon.awscdk.services.cognito.CognitoDomainOptions;
import software.amazon.awscdk.services.cognito.Mfa;
import software.amazon.awscdk.services.cognito.OAuthFlows;
import software.amazon.awscdk.services.cognito.OAuthSettings;
import software.amazon.awscdk.services.cognito.PasswordPolicy;
import software.amazon.awscdk.services.cognito.SignInAliases;
import software.constructs.Construct;

public interface UserPools {

        static UserPool create(Construct scope) {
                return UserPool.Builder.create(scope, "UserPool")
                                .userPoolName("agent-core-gateway-users")
                                .selfSignUpEnabled(true)
                                .signInAliases(SignInAliases.builder()
                                                .email(true)
                                                .build())
                                .passwordPolicy(PasswordPolicy.builder()
                                                .minLength(8)
                                                .requireLowercase(true)
                                                .requireUppercase(true)
                                                .requireDigits(false)
                                                .requireSymbols(false)
                                                .build())
                                .mfa(Mfa.OPTIONAL)
                                .removalPolicy(RemovalPolicy.DESTROY)

                                .build();
        }

        static UserPoolClient createClient(Construct scope, UserPool userPool) {
                return UserPoolClient.Builder.create(scope, "UserPoolClient")
                                .userPoolClientName("agent-core-gateway-client")
                                .userPool(userPool)
                                .generateSecret(false)
                                .authFlows(AuthFlow.builder()
                                                .userPassword(true)
                                                .build())
                                .oAuth(OAuthSettings.builder()
                                                .flows(OAuthFlows.builder()
                                                                .implicitCodeGrant(true)
                                                                .build())
                                                .build())
                                .accessTokenValidity(Duration.hours(8))
                                .build();
        }

    static UserPoolDomain createUserPoolDomain(Construct scope, UserPool userPool, String domainPrefix) {
        var domain = UserPoolDomain.Builder.create(scope, domainPrefix + "-domain")
                .userPool(userPool)
                .cognitoDomain(CognitoDomainOptions.builder()
                        .domainPrefix(domainPrefix)
                        .build())
                .build();

        CfnOutput.Builder.create(scope, domainPrefix + "-domain-url")
                .value(domain.baseUrl())
                .description("Cognito Hosted UI Domain")
                .build();

        return domain;
    }        

}
