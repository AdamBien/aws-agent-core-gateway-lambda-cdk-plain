package airhacks;

import airhacks.qacg.lambda.control.Functions;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class AgentCoreGatewayStack extends Stack {
            

    public AgentCoreGatewayStack(Construct scope, String appName, StackProps stackProps) {
        super(scope, ConventionalDefaults.stackName(appName, "agent-core-gateway"), stackProps);
        var functionName = "airhacks_BedrockGatewayEventListener";
        var functionHandler = ConventionalDefaults.functionHandler;
        var function = Functions.createFunction(scope, functionName, functionHandler);
    }
}
