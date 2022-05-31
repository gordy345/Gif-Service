package com.alfa.test_task.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "${exchangeRateServiceName}", url = "${exchangeRateBaseURL}")
public interface ExchangeRateClient {

    @GetMapping("/latest.json")
    String getLatestExchangeRate();

    @GetMapping("/historical/{date}.json")
    String getHistoricalExchangeRate(@PathVariable String date);
}
