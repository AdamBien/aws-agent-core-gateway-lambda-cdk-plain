package airhacks.qacg.gateway.control;

import software.amazon.awscdk.services.bedrockagentcore.CfnGateway;
import software.amazon.awscdk.services.iam.Role;
import software.amazon.awscdk.services.iam.ServicePrincipal;
import software.amazon.awscdk.services.lambda.IFunction;
import software.constructs.Construct;

public interface AgentCoreGateway {

    public static CfnGateway create(Construct scope, IFunction function){
        var gatewayRole = Role.Builder.create(scope, "GatewayRole")
                .assumedBy(ServicePrincipal.Builder.create("bedrock.amazonaws.com").build())
                .build();

        function.grantInvoke(gatewayRole);
        /*
         * https://docs.aws.amazon.com/AWSCloudFormation/latest/TemplateReference/aws-resource-bedrockagentcore-gateway.html#cfn-bedrockagentcore-gateway-authorizertype
         * http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-bedrockagentcore-gateway.html#cfn-bedrockagentcore-gateway-protocoltype
         */
        return CfnGateway.Builder.create(scope, "AgentCoreGateway")
                .name("quarkus-agent-core-gateway")
                .authorizerType("AWS_IAM")
                .protocolType("MCP")
                .roleArn(gatewayRole.getRoleArn())
                .build();
    }

}
