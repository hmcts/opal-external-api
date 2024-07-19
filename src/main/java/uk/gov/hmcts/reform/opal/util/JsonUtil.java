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
}
