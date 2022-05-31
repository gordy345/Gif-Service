package com.alfa.test_task.services;

import com.alfa.test_task.clients.ExchangeRateClient;
import com.alfa.test_task.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.alfa.test_task.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceImplTest {

    @Mock
    private ExchangeRateClient exchangeRateClient;

    @Mock
    private JsonUtils jsonUtils;

    @InjectMocks
    private final ExchangeRateService exchangeRateService = new ExchangeRateServiceImpl();

    @Test
    void decideSearchQuery() {
        when(exchangeRateClient.getLatestExchangeRate()).thenReturn(EMPTY_STRING);
        when(exchangeRateClient.getHistoricalExchangeRate(anyString())).thenReturn(EMPTY_STRING);
        when(jsonUtils.extractExchangeRate(anyString(), anyString())).thenReturn(EXCHANGE_RATE);
        String result = exchangeRateService.decideSearchQuery(CURRENCY);
        assertEquals(UP_SEARCH_QUERY, result);
        verify(exchangeRateClient).getLatestExchangeRate();
        verify(exchangeRateClient).getHistoricalExchangeRate(anyString());
        verify(jsonUtils, times(2)).extractExchangeRate(anyString(), anyString());
    }
}