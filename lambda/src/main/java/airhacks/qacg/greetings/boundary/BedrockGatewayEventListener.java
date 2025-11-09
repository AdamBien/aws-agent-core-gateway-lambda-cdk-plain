package airhacks.qacg.greetings.boundary;

import airhacks.qacg.greetings.control.EventProcessor;
import airhacks.qacg.greetings.entity.BedrockGatewayEvent;
import airhacks.qacg.logging.control.Log;

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
     * @param awsEvent AWS service event (EventBridge, SNS, SQS, etc.)
     */
    public void onEvent(Object awsEvent) {
        Log.info("event received: %s", awsEvent);
        var domainEvent = extract(awsEvent);
        Log.info("event converted: %s", domainEvent);
        EventProcessor.process(domainEvent);
        Log.info("event processed: %s", domainEvent);
    }

    /**
     * Transforms AWS service event into domain-specific representation.
     * Implement actual parsing logic based on the specific AWS service event structure
     * (e.g., EventBridge detail, SNS Message, SQS body, CodeBuild state change)
     *
     * @param event AWS event
     * @return domain specific payload
     */
    static BedrockGatewayEvent extract(Object event){
        return new BedrockGatewayEvent(event.toString());
    }
    
}
