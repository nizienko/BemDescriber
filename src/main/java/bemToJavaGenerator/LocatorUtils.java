package bemToJavaGenerator;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by def on 02.11.16.
 */
public class LocatorUtils {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String getLocatorByClass(String clazz) {
        return String.format(".//*[contains(concat(' ', @class, ' '), ' %s ')]", clazz);
    }
}
