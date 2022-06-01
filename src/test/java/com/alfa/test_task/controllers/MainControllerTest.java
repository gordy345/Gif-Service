package com.alfa.test_task.controllers;

import com.alfa.test_task.services.ExchangeRateService;
import com.alfa.test_task.services.GifService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.alfa.test_task.TestConstants.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
class MainControllerTest {

    @MockBean
    private GifService gifService;

    @MockBean
    private ExchangeRateService exchangeRateService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findGif() throws Exception {
        when(exchangeRateService.decideSearchQuery(anyString())).thenReturn(SEARCH_QUERY);
        when(gifService.findGif(anyString())).thenReturn(GIF_URL);
        mockMvc.perform(get(BASE_URL + "/gif-for-currency/{currency}", CURRENCY))
                .andExpect(status().isOk())
                .andExpect(model().attribute(GIF_MODEL_ATTRIBUTE, GIF_URL));
    }
}