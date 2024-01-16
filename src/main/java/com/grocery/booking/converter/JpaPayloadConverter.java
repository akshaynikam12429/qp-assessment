package com.grocery.booking.converter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grocery.booking.dto.Grocery;
import jakarta.persistence.AttributeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JpaPayloadConverter implements AttributeConverter<List<Grocery>, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Grocery> groceries) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(groceries);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Failed to process JSON");
        }
        return jsonString;
    }

    @Override
    public List<Grocery> convertToEntityAttribute(String dbData) {

        List<Grocery> groceries = new ArrayList<>();
        try {
            // convert json to list of POJO
            groceries = objectMapper.readValue(dbData, new TypeReference<List<Grocery>>(){});
        } catch (IOException ex) {
            try {
                throw new JsonParseException(ex.getMessage());
            } catch (JsonParseException e) {
                throw new RuntimeException(e);
            }
        }
        return groceries;

    }
}
