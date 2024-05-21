package com.mok.consumoapimok.service;

import com.mok.consumoapimok.models.PokemonApiResponse;
import com.mok.consumoapimok.models.PokemonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class PokemonApiClient {

    private static final Logger logger = LoggerFactory.getLogger(PokemonApiClient.class);

    private final RestTemplate restTemplate;

    public PokemonApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokemonApiResponse fetchPokemonData(String url) {
        try {
            ResponseEntity<PokemonApiResponse> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    PokemonApiResponse.class
            );
            return responseEntity.getBody();
        } catch (Exception e) {
            logger.error("Error buscando datos del pokemon", url, e);
            return null;
        }
    }

    public PokemonData fetchPokemonDetails(String url) {
        try {
            ResponseEntity<PokemonData> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    PokemonData.class
            );
            return responseEntity.getBody();
        } catch (Exception e) {
            logger.error("Error buscando datos desde la url", url, e);
            return null;
        }
    }
}
