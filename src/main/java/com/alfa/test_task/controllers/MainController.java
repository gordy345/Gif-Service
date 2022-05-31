package com.alfa.test_task.controllers;

import com.alfa.test_task.services.ExchangeRateService;
import com.alfa.test_task.services.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {

    private final ExchangeRateService exchangeRateService;
    private final GifService gifService;

    @GetMapping("/gif-for-currency/{currency}")
    public String findGif(@PathVariable String currency, Model model) {
        String searchQuery = exchangeRateService.decideSearchQuery(currency);
        model.addAttribute("gif", gifService.findGif(searchQuery));
        return "gif";
    }
}
