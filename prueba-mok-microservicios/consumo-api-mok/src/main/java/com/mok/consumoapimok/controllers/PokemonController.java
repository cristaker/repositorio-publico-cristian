package com.mok.consumoapimok.controllers;

import com.mok.consumoapimok.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private static final Logger logger = LoggerFactory.getLogger(PokemonController.class);

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/fetch-and-send")
    public ResponseEntity<String> fetchAndSendPokemonData() {
        try {
            pokemonService.fetchDataAndSendToKafka();
            return ResponseEntity.ok("Datos enviados correctamente");
        } catch (RestClientException e) {
            logger.error("Error buscando datos del pokemon", e);
            return ResponseEntity.status(400).body("Error enviando datos" + e.getMessage());
        } catch (Exception e) {
            logger.error("Error enviando datos", e);
            return ResponseEntity.status(500).body("Error enviando datos" + e.getMessage());
        }
    }
}
