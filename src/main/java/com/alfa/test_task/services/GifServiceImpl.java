package com.alfa.test_task.services;

import com.alfa.test_task.clients.GifClient;
import com.alfa.test_task.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GifServiceImpl implements GifService {

    private final GifClient gifClient;
    private final JsonUtils jsonUtils;

    public String findGif(String searchQuery) {
        log.info("Finding gif for query: " + searchQuery);
        String jsonResult = gifClient.searchGif(searchQuery);
        return jsonUtils.extractRandomGifUrl(jsonResult);
    }


}
