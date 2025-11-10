package airhacks.acgl.gateway.control;

import software.amazon.awscdk.services.bedrockagentcore.CfnGateway;
import software.amazon.awscdk.services.bedrockagentcore.CfnGatewayTarget;
import software.amazon.awscdk.services.lambda.IFunction;
import software.constructs.Construct;

import java.util.List;

public interface AgentCoreGatewayTarget {

    static CfnGatewayTarget create(Construct scope, CfnGateway gateway, IFunction function) {
        var inputSchema = CfnGatewayTarget.SchemaDefinitionProperty.builder()
                .type("object")
                .build();

        var toolDefinition = CfnGatewayTarget.ToolDefinitionProperty.builder()
                .name("mcp-tool")
                .description("MCP Lambda tool")
                .inputSchema(inputSchema)
                .build();

        var toolSchema = CfnGatewayTarget.ToolSchemaProperty.builder()
                .inlinePayload(List.of(toolDefinition))
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

        /**
         * https://docs.aws.amazon.com/AWSCloudFormation/latest/TemplateReference/aws-properties-bedrockagentcore-gatewaytarget-credentialproviderconfiguration.html#cfn-bedrockagentcore-gatewaytarget-credentialproviderconfiguration-credentialprovidertype
         */
        var credentialProvider = CfnGatewayTarget.CredentialProviderConfigurationProperty.builder()
                .credentialProviderType("GATEWAY_IAM_ROLE")
                .build();

        return CfnGatewayTarget.Builder.create(scope, "AgentCoreGatewayTarget")
                .name("quarkus-agent-target")
                .gatewayIdentifier(gateway.getAttrGatewayIdentifier())
                .targetConfiguration(targetConfig)
                .credentialProviderConfigurations(List.of(credentialProvider))
                .build();
    }
}
