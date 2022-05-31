package com.alfa.test_task.services;

import com.alfa.test_task.clients.GifClient;
import com.alfa.test_task.utils.JsonUtils;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class GifServiceImpl implements GifService {

    private GifClient gifClient;
    private JsonUtils jsonUtils;

    @Autowired
    public GifServiceImpl(GifClient gifClient, JsonUtils jsonUtils) {
        this.gifClient = gifClient;
        this.jsonUtils = jsonUtils;
    }

    public String findGif(String searchQuery) {
        log.info("Finding gif for query: " + searchQuery);
        String jsonResult = gifClient.searchGif(searchQuery);
        return jsonUtils.extractRandomGifUrl(jsonResult);
    }


}
