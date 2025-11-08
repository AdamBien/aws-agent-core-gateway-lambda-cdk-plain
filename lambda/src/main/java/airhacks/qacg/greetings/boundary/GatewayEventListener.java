package airhacks.lambda.example.boundary;

import airhacks.lambda.example.control.EventProcessor;
import airhacks.lambda.example.entity.ExampleEvent;
import airhacks.logging.control.Log;

/**
*/
public class GatewayEventListener {
    
    static String message = System.getenv("message");

    public GatewayEventListener() {
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
    static ExampleEvent extract(Object event){
        return new ExampleEvent(event.toString());
    }
    
}
