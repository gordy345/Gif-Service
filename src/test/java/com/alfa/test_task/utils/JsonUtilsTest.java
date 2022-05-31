package com.alfa.test_task.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static com.alfa.test_task.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilsTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JsonUtils jsonUtils = new JsonUtils(objectMapper);

    @Test
    void extractRandomGifUrl() {
        String result = jsonUtils.extractRandomGifUrl(JSON_RESULT_VALID);
        assertEquals(GIF_URL, result);
    }

    @Test
    void extractRandomGifNoData() {
        String result = jsonUtils.extractRandomGifUrl(JSON_RESULT_EMPTY_DATA);
        assertEquals(ERROR_IMAGE_URL, result);
    }

    @Test
    void extractRandomGifNoJson() {
        String result = jsonUtils.extractRandomGifUrl(EMPTY_STRING);
        assertEquals(ERROR_IMAGE_URL, result);
    }

    @Test
    void extractRandomGifBadJson() {
        String result = jsonUtils.extractRandomGifUrl(JSON_RESULT_INVALID);
        assertEquals(ERROR_IMAGE_URL, result);
    }
}