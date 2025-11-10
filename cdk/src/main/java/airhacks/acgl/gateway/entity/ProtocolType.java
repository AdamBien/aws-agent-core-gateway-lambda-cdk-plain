package airhacks.acgl.gateway.entity;

/**
 * https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-bedrockagentcore-gateway.html#cfn-bedrockagentcore-gateway-protocoltype
 */
public enum ProtocolType {

    MCP;

    public String value() {
        return name();
    }

}
