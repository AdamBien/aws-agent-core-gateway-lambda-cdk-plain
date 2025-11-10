package airhacks;

import airhacks.acgl.cognito.boundary.CognitoStack;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Tags;

public interface CDKApp {
    String appName = "agent-core-gateway-lambda";

    static void main(String... args) {

        var app = new App();
        Tags.of(app).add("project", "constructions.cloud");
        Tags.of(app).add("environment", "dev");
        Tags.of(app).add("application", appName);

        var configuration = new Configuration(appName);
        var stackProps = configuration.stackProperties();

        var cognitoStack = new CognitoStack(app, appName, stackProps);
        var userPoolId = cognitoStack.userPoolId();
        var userPoolClientId = cognitoStack.userPoolClientId();
        new AgentCoreGatewayStack(app, appName, userPoolId, userPoolClientId, stackProps);
        app.synth();
    }
}
