package airhacks;

public interface ConventionalDefaults {
    int memory = 1769; 
    int timeout = 30;

    static String stackName(String appName, String stackName) {
        return "%s-%s-stack".formatted(appName, stackName);
    }
}
