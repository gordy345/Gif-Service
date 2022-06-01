package com.alfa.test_task.controllers;

import com.alfa.test_task.clients.ExchangeRateClient;
import com.alfa.test_task.clients.GifClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.alfa.test_task.TestConstants.*;
import static com.alfa.test_task.utils.ExceptionMessages.EXCHANGE_RATE_NOT_FOUND_MESSAGE;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerIntegrationTest {

    @Value("${errorImageURL}")
    private String errorImageURL;

    @MockBean
    private GifClient gifClient;

    @MockBean
    private ExchangeRateClient exchangeRateClient;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findGif() throws Exception {
        when(exchangeRateClient.getLatestExchangeRate()).thenReturn(JSON_EXCHANGE_RATE_VALID);
        when(exchangeRateClient.getHistoricalExchangeRate(anyString())).thenReturn(JSON_EXCHANGE_RATE_VALID);
        when(gifClient.searchGif(anyString())).thenReturn(JSON_GIF_VALID);
        mockMvc.perform(get(BASE_URL + "/gif-for-currency/{currency}", CURRENCY))
                .andExpect(status().isOk())
                .andExpect(model().attribute(GIF_MODEL_ATTRIBUTE, GIF_URL));
    }

    @ParameterizedTest
    @ValueSource(strings = {JSON_EXCHANGE_RATE_INVALID, EMPTY_STRING})
    void findGifErrorJsonLatestExchangeRate(String latestJson) throws Exception {
        when(exchangeRateClient.getLatestExchangeRate()).thenReturn(latestJson);
        when(exchangeRateClient.getHistoricalExchangeRate(anyString())).thenReturn(JSON_EXCHANGE_RATE_VALID);
        when(gifClient.searchGif(anyString())).thenReturn(JSON_GIF_VALID);
        mockMvc.perform(get(BASE_URL + "/gif-for-currency/{currency}", CURRENCY))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(EXCHANGE_RATE_NOT_FOUND_MESSAGE + CURRENCY));
    }

    @ParameterizedTest
    @ValueSource(strings = {JSON_EXCHANGE_RATE_INVALID, EMPTY_STRING})
    void findGifErrorJsonHistoricalExchangeRate(String historicalJson) throws Exception {
        when(exchangeRateClient.getLatestExchangeRate()).thenReturn(JSON_EXCHANGE_RATE_VALID);
        when(exchangeRateClient.getHistoricalExchangeRate(anyString())).thenReturn(historicalJson);
        when(gifClient.searchGif(anyString())).thenReturn(JSON_GIF_VALID);
        mockMvc.perform(get(BASE_URL + "/gif-for-currency/{currency}", CURRENCY))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(EXCHANGE_RATE_NOT_FOUND_MESSAGE + CURRENCY));
    }

    @ParameterizedTest
    @ValueSource(strings = {JSON_GIF_INVALID, JSON_GIF_EMPTY_DATA, EMPTY_STRING})
    void findGifErrorJsonGif(String gifJson) throws Exception {
        when(exchangeRateClient.getLatestExchangeRate()).thenReturn(JSON_EXCHANGE_RATE_VALID);
        when(exchangeRateClient.getHistoricalExchangeRate(anyString())).thenReturn(JSON_EXCHANGE_RATE_VALID);
        when(gifClient.searchGif(anyString())).thenReturn(gifJson);
        mockMvc.perform(get(BASE_URL + "/gif-for-currency/{currency}", CURRENCY))
                .andExpect(status().isOk())
                .andExpect(model().attribute(GIF_MODEL_ATTRIBUTE, errorImageURL));
    }
}
