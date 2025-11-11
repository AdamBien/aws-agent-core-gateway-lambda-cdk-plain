package airhacks.qacg.greetings.control;

import airhacks.qacg.greetings.entity.BedrockGatewayEvent;
import airhacks.qacg.logging.control.Log;

/**
 * Processes domain events extracted from AWS Bedrock Agent Gateway.
 */
public interface EventProcessor {

    /**
     *
     * @param event domain event to process
     * @return greeting message
     */
    static String process(BedrockGatewayEvent event) {
        Log.info("Processing domain event with name: %s", event.name());
        var greeting = "hello, " + event.name();
        Log.info("Domain event processed successfully");
        return greeting;
    }
}