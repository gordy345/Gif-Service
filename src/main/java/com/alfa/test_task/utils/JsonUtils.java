package com.alfa.test_task.utils;

import com.alfa.test_task.exceptions.ExchangeRateNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonUtils {

    @Value("${errorImageURL}")
    private String errorImagePath;

    private final ObjectMapper objectMapper;

    public String extractRandomGifUrl(String json) {
        log.info("Extracting random gif url from json result");
        String url;
        try {
            ArrayNode data = (ArrayNode) objectMapper.readTree(json).get("data");
            if (data.size() == 0) {
                log.info("Gif wasn't found");
                return errorImagePath;
            }
            int randomIdx = (int) (Math.random() * data.size());
            url = data.get(randomIdx).get("images").get("original").get("url").asText();
        } catch (JsonProcessingException | NullPointerException e) {
            log.warn("Exception happened while parsing JSON, message: " + e.getMessage());
            return errorImagePath;
        }
        return url != null ? url : errorImagePath;
    }

    public double extractExchangeRate(String json, String currency) {
        log.info("Extracting exchange rate from json for currency: " + currency);
        try {
            return objectMapper.readValue(json, ObjectNode.class).get("rates").get(currency).asDouble();
        } catch (JsonProcessingException | NullPointerException e) {
            log.warn("Exception happened while parsing JSON, message: " + e.getMessage());
            throw new ExchangeRateNotFoundException("We didn't found exchange rate for currency: " + currency);
        }
    }
}
