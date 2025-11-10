package airhacks.acgl.gateway.control;

import software.amazon.awscdk.services.bedrockagentcore.CfnGateway;
import software.amazon.awscdk.services.iam.Role;
import software.amazon.awscdk.services.iam.ServicePrincipal;
import software.amazon.awscdk.services.lambda.IFunction;
import software.amazon.awscdk.services.lambda.Permission;
import software.constructs.Construct;
import java.util.List;

import airhacks.acgl.gateway.entity.AuthorizerType;
import airhacks.acgl.gateway.entity.ProtocolType;

/**
 * https://docs.aws.amazon.com/AWSCloudFormation/latest/TemplateReference/aws-resource-bedrockagentcore-gateway.html
 */
public interface AgentCoreGateway {

    static CfnGateway create(Construct scope, IFunction function, String region, String userPoolId, String userPoolClientId){
        var gatewayRole = Role.Builder.create(scope, "GatewayRole")
                .assumedBy(ServicePrincipal.Builder.create("bedrock-agentcore.amazonaws.com").build())
                .build();

        function.grantInvoke(gatewayRole);
        function.addPermission("AllowBedrockGateway",
                Permission.builder()
                        .principal(ServicePrincipal.Builder.create("bedrock-agentcore.amazonaws.com").build())
                        .action("lambda:InvokeFunction")
                        .build());

        var discoveryUrl = String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/openid-configuration",
                region,
                userPoolId);

        var jwtConfig = CfnGateway.CustomJWTAuthorizerConfigurationProperty.builder()
                .discoveryUrl(discoveryUrl)
                .allowedClients(List.of(userPoolClientId))
                .build();

        var authConfig = CfnGateway.AuthorizerConfigurationProperty.builder()
                .customJwtAuthorizer(jwtConfig)
                .build();

        /*
         * https://docs.aws.amazon.com/AWSCloudFormation/latest/TemplateReference/aws-resource-bedrockagentcore-gateway.html#cfn-bedrockagentcore-gateway-authorizertype
         * http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-bedrockagentcore-gateway.html#cfn-bedrockagentcore-gateway-protocoltype
         */
        return CfnGateway.Builder.create(scope, "AgentCoreGateway")
                .name("quarkus-agent-core-gateway")
                .authorizerType(AuthorizerType.CUSTOM_JWT.value())
                .authorizerConfiguration(authConfig)
                .protocolType(ProtocolType.MCP.value())
                .roleArn(gatewayRole.getRoleArn())
                .exceptionLevel("DEBUG")
                .build();
    }

}
