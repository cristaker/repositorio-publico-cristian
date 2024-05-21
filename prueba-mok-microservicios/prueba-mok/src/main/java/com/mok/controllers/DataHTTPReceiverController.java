package com.mok.controllers;

import com.mok.models.PokemonHttp;
import com.mok.services.PokemonHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataHTTPReceiverController {

    @Autowired
    private PokemonHttpService pokemonHttpService;

    @PostMapping("/receive-pokemon-data")
    public ResponseEntity<String> receivePokemonData(@RequestBody PokemonHttp pokemonData) {
        System.out.println("Datos recibidos desde el otro microservicio:");
        System.out.println(pokemonData);
        pokemonHttpService.pokemonSaveData(pokemonData);

        return ResponseEntity.ok("Datos recibidos correctamente");
    }

    @GetMapping("/get-paginated-pokemon-data")
    public ResponseEntity<Page<PokemonHttp>> getPaginatedPokemonData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<PokemonHttp> paginatedData = pokemonHttpService.getPaginatedPokemonData(page, size);
        return ResponseEntity.ok(paginatedData);
    }
}
