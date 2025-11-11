package airhacks.qacg.greetings.boundary;

import airhacks.qacg.greetings.control.EventProcessor;
import airhacks.qacg.greetings.entity.BedrockGatewayEvent;
import airhacks.qacg.logging.control.Log;
import java.util.Map;

/**
*/
public class BedrockGatewayEventListener {
    
    static String message = System.getenv("message");

    public BedrockGatewayEventListener() {
        Log.info("initialized with configuration: %s", message);
    }

    /**
     * Lambda function entry point - handles incoming AWS service events.
     * This method is invoked by the Lambda runtime for each event trigger.
     *
     * @param awsEvent AWS service event containing name parameter
     * @return greeting message
     */
    public String onEvent(Map<String, Object> awsEvent) {
        Log.info("event received: %s", awsEvent);
        var domainEvent = extract(awsEvent);
        Log.info("event converted: %s", domainEvent);
        var result = EventProcessor.process(domainEvent);
        Log.info("event processed: %s", domainEvent);
        return result;
    }

    /**
     * Transforms AWS service event into domain-specific representation.
     * Extracts the name parameter from the event.
     *
     * @param event AWS event containing name parameter
     * @return domain specific event with name
     */
    static BedrockGatewayEvent extract(Map<String, Object> event){
        var name = event.getOrDefault("name", "World").toString();
        return new BedrockGatewayEvent(name);
    }
    
}
