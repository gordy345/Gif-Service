package com.alfa.test_task;

import org.springframework.beans.factory.annotation.Value;

public class TestConstants {

    public static final String SEARCH_QUERY = "Search Query";
    public static final String GIF_URL = "URL";
    public static final String JSON_RESULT_EMPTY_DATA = "{\"data\":[]}";
    public static final String JSON_RESULT_VALID = "{\"data\":[{\"images\":{\"original\":{\"url\":\"URL\"}}}]}";
    public static final String JSON_RESULT_INVALID = "{\"data\":[{\"ima\":{\"original\":{\"url\":\"URL\"}}}]}";
    public static final String EMPTY_STRING = "";
    @Value("${errorImageURL}")
    public static String ERROR_IMAGE_URL;
}
