package com.alfa.test_task.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${gifServiceName}", url = "${gifBaseURL}")
public interface GifClient {

    @GetMapping(value = "/search")
    String searchGif(@RequestParam String q);
}
