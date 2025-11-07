package airhacks.qacg.gateway.control;

import software.amazon.awscdk.services.bedrockagentcore.CfnGateway;
import software.amazon.awscdk.services.bedrockagentcore.CfnGatewayTarget;
import software.amazon.awscdk.services.lambda.IFunction;
import software.constructs.Construct;

import java.util.List;

public interface AgentCoreGatewayTarget {

    static CfnGatewayTarget create(Construct scope, CfnGateway gateway, IFunction function) {
        var toolSchema = CfnGatewayTarget.ToolSchemaProperty.builder()
                .inlinePayload(List.of())
                .build();
        var mcpLambdaConfig = CfnGatewayTarget.McpLambdaTargetConfigurationProperty.builder()
                .lambdaArn(function.getFunctionArn())
                .toolSchema(toolSchema)
                .build();

        var mcpTargetConfig = CfnGatewayTarget.McpTargetConfigurationProperty.builder()
                .lambda(mcpLambdaConfig)
                .build();

        var targetConfig = CfnGatewayTarget.TargetConfigurationProperty.builder()
                .mcp(mcpTargetConfig)
                .build();

        return CfnGatewayTarget.Builder.create(scope, "AgentCoreGatewayTarget")
                .name("quarkus-agent-target")
                .gatewayIdentifier(gateway.getAttrGatewayIdentifier())
                .targetConfiguration(targetConfig)
                .credentialProviderConfigurations(List.of())
                .build();
    }
}
