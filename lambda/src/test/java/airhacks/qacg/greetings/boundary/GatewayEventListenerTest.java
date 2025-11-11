package airhacks.qacg.greetings.boundary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import airhacks.qacg.greetings.entity.BedrockGatewayEvent;
import java.util.Map;


/**
 * Tests event extraction and greeting logic.
 */
public class GatewayEventListenerTest {

    @Test
    void awsToDomainEventConversion() {
        Map<String, Object> awsEvent = Map.of("name", "Alice");
        var actualEvent = BedrockGatewayEventListener.extract(awsEvent);
        var expectedEvent = new BedrockGatewayEvent("Alice");
        assertThat(actualEvent).isEqualTo(expectedEvent);
    }

    @Test
    void greetingWithName() {
        var listener = new BedrockGatewayEventListener();
        Map<String, Object> event = Map.of("name", "Duke");
        var result = listener.onEvent(event);
        assertThat(result).isEqualTo("hello, Duke");
    }

    @Test
    void greetingWithoutName() {
        var listener = new BedrockGatewayEventListener();
        var event = Map.<String, Object>of();
        var result = listener.onEvent(event);
        assertThat(result).isEqualTo("hello, World");
    }
}
