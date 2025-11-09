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
     */
    static void process(BedrockGatewayEvent event) {
        Log.info("Processing domain event with payload: %s", event.payload());

        Log.info("Domain event processed successfully");
    }
}