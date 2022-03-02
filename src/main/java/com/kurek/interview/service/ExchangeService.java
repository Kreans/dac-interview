package com.kurek.interview.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


@Service
public class ExchangeService {

    Logger logger = LoggerFactory.getLogger(ExchangeService.class);
    
    public static final String NBP_URL = "http://api.nbp.pl/api/exchangerates/rates/A/EUR?format=json";

    public BigDecimal exchangeEuroCentsToPln() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            final String response = restTemplate.getForObject(NBP_URL, String.class);
            return getRatio(response);
        } catch (Exception e) {
            logger.debug(String.format("Error occurred while getting exchange %s", e.getCause()));
            return null;
        }
    }

    private BigDecimal getRatio(String response) throws JSONException {
        final JSONObject obj = new JSONObject(response);

        return obj.getJSONArray("rates")
                .getJSONObject(0)
                .getBigDecimal("mid");
    }
}
