package airhacks.qacg.greetings.boundary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import airhacks.qacg.greetings.entity.BedrockGatewayEvent;


/**
 * Tests event extraction logic.
 * Real-world implementations should test with actual AWS event structures
 * from EventBridge, SNS, SQS, CodeBuild, etc. to ensure proper parsing and
 * transformation.
 */
public class GatewayEventListenerTest {

    @Test
    void awsToDomainEventConversion() {
        var awsEvent = """
                {
                    "type":"aws",
                    "payload:"test"
                }
                """;
        var actualEvent = BedrockGatewayEventListener.extract(awsEvent);
        var expectedEvent = new BedrockGatewayEvent(awsEvent);
        assertThat(actualEvent).isEqualTo(expectedEvent);
    }
}
