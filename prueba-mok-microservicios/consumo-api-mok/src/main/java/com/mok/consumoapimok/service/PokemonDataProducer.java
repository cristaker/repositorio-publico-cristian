package com.mok.consumoapimok.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mok.consumoapimok.models.PokemonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class PokemonDataProducer {

    private static final Logger logger = LoggerFactory.getLogger(PokemonDataProducer.class);
    private static final String TOPIC = "pokemon-data";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PokemonDataProducer(KafkaTemplate<String, String> kafkaTemplate, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendPokemonDataToKafka(PokemonData pokemonData) {
        try {
            String message = objectMapper.writeValueAsString(pokemonData);
            kafkaTemplate.send(TOPIC, message);
        } catch (JsonProcessingException e) {
            logger.error("Error al convertir el objeto", e);
        } catch (Exception e) {
            logger.error("Error al enviar el mensaje a Kafka", e);
        }
    }

    public void sendPokemonDataToHttp(PokemonData pokemonData) {
        String destinationUrl = "http://localhost:8001";
        String destinationEndpoint = destinationUrl + "/receive-pokemon-data";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PokemonData> requestEntity = new HttpEntity<>(pokemonData, headers);
        try {
            restTemplate.exchange(destinationEndpoint, HttpMethod.POST, requestEntity, Void.class);
        } catch (RestClientException e) {
            logger.error("Error al enviar el mensaje HTTP", destinationEndpoint, e);
        } catch (Exception e) {
            logger.error("Error al enviar el mensaje HTTP", e);
        }
    }
}

