package airhacks.acgl.gateway.entity;

import java.util.Map;

public interface ToolSchema {
    
    static Map<String,Object> create(){
    return Map.of(
                        "name", Map.of(
                                "type", "string",
                                "description", "The name of the person to greet"
                        )
                );        
    }
}
