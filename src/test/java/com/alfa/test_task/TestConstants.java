package com.alfa.test_task;

import org.springframework.beans.factory.annotation.Value;

public class TestConstants {

    public static final String SEARCH_QUERY = "Search Query";
    public static final String GIF_URL = "URL";
    public static final double EXCHANGE_RATE = 3.6731;
    public static final String CURRENCY = "USD";
    public static final String JSON_GIF_EMPTY_DATA = "{\"data\":[]}";
    public static final String JSON_GIF_VALID = "{\"data\":[{\"images\":{\"original\":{\"url\":\"URL\"}}}]}";
    public static final String JSON_GIF_INVALID = "{\"data\":[{\"ima\":{\"original\":{\"url\":\"URL\"}}}]}";
    public static final String JSON_EXCHANGE_RATE_VALID = "{\"rates\":{\"USD\":3.6731}}";
    public static final String JSON_EXCHANGE_RATE_INVALID = "{\"rat\":{\"USD\":3.6731}}";
    public static final String EMPTY_STRING = "";
    public static final String BASE_URL = "http://localhost:8080/api";
    public static final String GIF_MODEL_ATTRIBUTE = "gif";

    @Value("${errorImageURL}")
    public static String ERROR_IMAGE_URL;

    @Value("${searchQueryUpRate}")
    public static String UP_SEARCH_QUERY;


}
