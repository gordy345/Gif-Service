package com.alfa.test_task.services;

import com.alfa.test_task.clients.GifClient;
import com.alfa.test_task.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.alfa.test_task.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GifServiceImplTest {

    @Mock
    private GifClient gifClient;

    @Mock
    private JsonUtils jsonUtils;

    private GifService gifService;


    @BeforeEach
    void setUp() {
        gifService = new GifServiceImpl(gifClient, jsonUtils);
    }

    @Test
    void searchGif() {
        when(gifClient.searchGif(anyString())).thenReturn(JSON_RESULT_EMPTY_DATA);
        when(jsonUtils.extractRandomGifUrl(anyString())).thenReturn(GIF_URL);
        String resultUrl = gifService.findGif(SEARCH_QUERY);
        assertEquals(GIF_URL, resultUrl);
        verify(gifClient).searchGif(anyString());
        verify(jsonUtils).extractRandomGifUrl(anyString());
    }
}