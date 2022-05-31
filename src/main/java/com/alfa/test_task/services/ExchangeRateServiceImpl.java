package com.alfa.test_task.services;

import com.alfa.test_task.clients.ExchangeRateClient;
import com.alfa.test_task.utils.JsonUtils;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@NoArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Value("${searchQueryUpRate}")
    private String searchQueryUpRate;

    @Value("${searchQueryDownRate}")
    private String searchQueryDownRate;

    private ExchangeRateClient exchangeRateClient;
    private JsonUtils jsonUtils;

    @Autowired
    public ExchangeRateServiceImpl(ExchangeRateClient exchangeRateClient, JsonUtils jsonUtils) {
        this.exchangeRateClient = exchangeRateClient;
        this.jsonUtils = jsonUtils;
    }

    @Override
    public String decideSearchQuery(String currency) {
        log.info("Deciding search query for currency: " + currency);
        String latestExchangeRateJson = exchangeRateClient.getLatestExchangeRate();
        String yesterdayExchangeRateJson =
                exchangeRateClient.getHistoricalExchangeRate(LocalDate.now().minusDays(1).toString());
        double todayExchangeRate = jsonUtils.extractExchangeRate(latestExchangeRateJson, currency);
        double yesterdayExchangeRate = jsonUtils.extractExchangeRate(yesterdayExchangeRateJson, currency);
        if (todayExchangeRate >= yesterdayExchangeRate) {
            return searchQueryUpRate;
        }
        return searchQueryDownRate;
    }
}
