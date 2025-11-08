package airhacks;

import airhacks.qacg.lambda.control.Functions;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.bedrock.agentcore.alpha.AgentRuntimeArtifact;
import software.amazon.awscdk.services.bedrock.agentcore.alpha.RuntimeNetworkConfiguration;
import software.constructs.Construct;

public class AgentCoreGatewayStack
        extends Stack {
            

    public AgentCoreGatewayStack(Construct scope, String appName, StackProps stackProps) {
        super(scope, ConventionalDefaults.stackName(appName, "agent-core-gateway"), stackProps);
        Functions.createFunction(scope, appName, appName);
        var agentRuntimeId = runtime.getAgentRuntimeId();
        CfnOutput.Builder.create(this, "AgentRuntimeId").value(agentRuntimeId).build();
        var agentArn = runtime.getAgentRuntimeArn();
        CfnOutput.Builder.create(this, "AgentArn").value(agentArn).build();
    }
}
