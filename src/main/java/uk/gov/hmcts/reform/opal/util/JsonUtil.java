package uk.gov.hmcts.reform.opal.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static <T> T convertJsonToObject(String rawJson, Class<T> clzz) {
        try {
            // Create an ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON string into the specified class
            return objectMapper.readValue(rawJson, clzz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }

    public static String convertObjectToJson(Object object) {
        try {
            // Create an ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Serialize the object into a JSON string
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }
}
