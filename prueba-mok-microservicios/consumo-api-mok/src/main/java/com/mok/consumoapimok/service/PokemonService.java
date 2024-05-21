package com.mok.consumoapimok.service;

import com.mok.consumoapimok.models.PokemonApiResponse;
import com.mok.consumoapimok.models.PokemonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class PokemonService {

    private static final Logger logger = LoggerFactory.getLogger(PokemonService.class);

    private final PokemonApiClient pokemonApiClient;
    private final PokemonDataProducer pokemonDataProducer;

    public PokemonService(PokemonApiClient pokemonApiClient, PokemonDataProducer pokemonDataProducer) {
        this.pokemonApiClient = pokemonApiClient;
        this.pokemonDataProducer = pokemonDataProducer;
    }

    public void fetchDataAndSendToKafka() {
        String nextUrl = "https://pokeapi.co/api/v2/pokemon";
        do {
            PokemonApiResponse response = null;
            try {
                response = pokemonApiClient.fetchPokemonData(nextUrl);
            } catch (Exception e) {
                logger.error("Error buscando datos desde la url", nextUrl, e);
            }

            if (response != null && response.getResults() != null) {
                for (PokemonData pokemonData : response.getResults()) {
                    try {
                        PokemonData detailedPokemonData = pokemonApiClient.fetchPokemonDetails(pokemonData.getUrl());
                        if (detailedPokemonData != null) {
                            pokemonDataProducer.sendPokemonDataToKafka(detailedPokemonData);
                            pokemonDataProducer.sendPokemonDataToHttp(detailedPokemonData);
                        } else {
                            logger.warn("No hay datos del pokemon", pokemonData.getName());
                        }
                    } catch (RestClientException e) {
                        logger.error("Error buscando datos del pokemon", pokemonData.getName(), e);
                    } catch (Exception e) {
                        logger.error("UError buscando datos del pokemon", pokemonData.getName(), e);
                    }
                }
            }

            if (response != null) {
                nextUrl = response.getNext();
            } else {
                nextUrl = null;
            }
        } while (nextUrl != null);
    }
}


