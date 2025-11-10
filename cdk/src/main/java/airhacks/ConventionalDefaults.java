package airhacks;

public interface ConventionalDefaults {
    int memory = 1769;
    int timeout = 30;

    public static String functionHandler = functionHandler("airhacks.qacg.greetings.boundary.BedrockGatewayEventListener");

    static String functionHandler(String fqn) {
        return fqn + "::onEvent";
    }

    static String stackName(String appName, String stackName) {
        return "%s-%s-stack".formatted(appName, stackName);
    }

    static String mainStackName(String appName) {
        return stackName(appName, "main");
    }
}
