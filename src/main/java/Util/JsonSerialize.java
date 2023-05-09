package Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerialize<T> {

    public String toJson(T dataObject) {
        ObjectMapper objectMapper=new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json ;
    }
}
