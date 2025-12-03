package airhacks.acgl.lambda.control;

import java.util.Map;

import airhacks.ConventionalDefaults;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.services.lambda.Architecture;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

public interface Functions{

    static Function createFunction(Construct app,String functionName,String functionHandler){
        return createFunction(app, functionName, functionHandler,Map.of(),ConventionalDefaults.memory,ConventionalDefaults.timeout);

    }

    static Function createFunction(Construct app,String functionName,String functionHandler, Map<String,String> configuration, int memory, int timeout) {
        return Function.Builder.create(app, functionName)
                .runtime(Runtime.JAVA_25)
                .architecture(Architecture.ARM_64)
                .code(Code.fromAsset("../lambda/target/function.jar"))
                .handler(functionHandler)
                .memorySize(memory)
                .functionName(functionName)
                .environment(configuration)
                .timeout(Duration.seconds(timeout))
                .build();
    }
}
