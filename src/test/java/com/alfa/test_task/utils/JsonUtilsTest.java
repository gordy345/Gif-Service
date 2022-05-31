package com.alfa.test_task.utils;

import com.alfa.test_task.exceptions.ExchangeRateNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static com.alfa.test_task.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonUtilsTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JsonUtils jsonUtils = new JsonUtils(objectMapper);

    @Test
    void extractRandomGifUrl() {
        String result = jsonUtils.extractRandomGifUrl(JSON_GIF_VALID);
        assertEquals(GIF_URL, result);
    }

    @Test
    void extractRandomGifNoData() {
        String result = jsonUtils.extractRandomGifUrl(JSON_GIF_EMPTY_DATA);
        assertEquals(ERROR_IMAGE_URL, result);
    }

    @Test
    void extractRandomGifNoJson() {
        String result = jsonUtils.extractRandomGifUrl(EMPTY_STRING);
        assertEquals(ERROR_IMAGE_URL, result);
    }

    @Test
    void extractRandomGifBadJson() {
        String result = jsonUtils.extractRandomGifUrl(JSON_GIF_INVALID);
        assertEquals(ERROR_IMAGE_URL, result);
    }

    @Test
    void extractExchangeRate() {
        double result = jsonUtils.extractExchangeRate(JSON_EXCHANGE_RATE_VALID, CURRENCY);
        assertEquals(EXCHANGE_RATE, result);
    }

    @Test
    void extractExchangeRateNoJson() {
        assertThrows(ExchangeRateNotFoundException.class,
                () -> jsonUtils.extractExchangeRate(EMPTY_STRING, CURRENCY));
    }

    @Test
    void extractExchangeRateBadJson() {
        assertThrows(ExchangeRateNotFoundException.class,
                () -> jsonUtils.extractExchangeRate(JSON_EXCHANGE_RATE_INVALID, CURRENCY));
    }

    @Test
    void extractExchangeRateBadCurrency() {
        assertThrows(ExchangeRateNotFoundException.class,
                () -> jsonUtils.extractExchangeRate(JSON_EXCHANGE_RATE_VALID, EMPTY_STRING));
    }
}