package airhacks.acgl.gateway.entity;

/**
 * https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-bedrockagentcore-gateway.html#cfn-bedrockagentcore-gateway-authorizertype
 */
public enum AuthorizerType {

    CUSTOM_JWT,
    AWS_IAM;

    public String value() {
        return name();
    }

}
