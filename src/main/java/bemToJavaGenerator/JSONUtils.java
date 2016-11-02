package bemToJavaGenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Created by def on 02.11.16.
 */
public class JSONUtils {
    public final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String getJSONString(Map map) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(map);
    }
}
