package com.alfa.test_task.services;

import com.alfa.test_task.clients.GifClient;
import com.alfa.test_task.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private final GifService gifService = new GifServiceImpl();

    @Test
    void searchGif() {
        when(gifClient.searchGif(anyString())).thenReturn(JSON_GIF_EMPTY_DATA);
        when(jsonUtils.extractRandomGifUrl(anyString())).thenReturn(GIF_URL);
        String resultUrl = gifService.findGif(SEARCH_QUERY);
        assertEquals(GIF_URL, resultUrl);
        verify(gifClient).searchGif(anyString());
        verify(jsonUtils).extractRandomGifUrl(anyString());
    }
}