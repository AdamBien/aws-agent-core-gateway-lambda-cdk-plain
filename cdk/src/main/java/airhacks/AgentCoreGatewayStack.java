package airhacks;

import airhacks.qacg.gateway.control.AgentCoreGateway;
import airhacks.qacg.gateway.control.AgentCoreGatewayTarget;
import airhacks.qacg.lambda.control.Functions;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class AgentCoreGatewayStack extends Stack {
            

    public AgentCoreGatewayStack(Construct scope, String appName, StackProps stackProps) {
        super(scope, ConventionalDefaults.stackName(appName, "agent-core-gateway"), stackProps);
        var functionName = "airhacks_BedrockGatewayEventListener";
        var functionHandler = ConventionalDefaults.functionHandler;
        var function = Functions.createFunction(this, functionName, functionHandler);
        var gateway = AgentCoreGateway.create(this, function);
        var target = AgentCoreGatewayTarget.create(this, gateway, function);

        CfnOutput.Builder.create(this, "GatewayURL")
                .value(gateway.getAttrGatewayUrl())
                .description("Agent Core Gateway URL")
                .build();

        CfnOutput.Builder.create(this, "GatewayId")
                .value(gateway.getAttrGatewayIdentifier())
                .description("Gateway ID - use this to create API keys via AWS CLI")
                .build();
    }
}
