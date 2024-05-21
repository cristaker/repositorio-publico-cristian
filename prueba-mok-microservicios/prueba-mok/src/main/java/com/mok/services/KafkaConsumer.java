package com.mok.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mok.exceptions.MessageProcessingException;
import com.mok.models.Pokemon;
import com.mok.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    private PokemonRepository pokemonRepository;

    private final ObjectMapper objectMapper;

    public KafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "pokemon-data", groupId = "pokemon-group")
    public void listen(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String name = jsonNode.get("name").asText();
            int height = jsonNode.get("height").asInt();
            int weight = jsonNode.get("weight").asInt();

            Pokemon pokemon = new Pokemon();
            pokemon.setName(name);
            pokemon.setHeight(height);
            pokemon.setWeight(weight);
            pokemonRepository.save(pokemon);

            System.out.println("mensaje procesado" + message);
        } catch (JsonProcessingException e) {
            throw new MessageProcessingException("Error al procesar el mensaje JSON" + message, e);
        } catch (Exception e) {
            throw new MessageProcessingException("Error al procesar el mensaje" + message, e);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void checkForNewMessages() {
        System.out.println("Verificando si hay nuevos mensajes");
    }
}
