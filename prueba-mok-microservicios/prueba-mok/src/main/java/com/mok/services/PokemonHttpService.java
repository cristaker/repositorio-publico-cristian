package com.mok.services;

import com.mok.exceptions.PokemonServiceException;
import com.mok.models.PokemonHttp;
import com.mok.repository.PokemonHttpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PokemonHttpService {

    @Autowired
    private PokemonHttpRepository pokemonHttpRepository;

    public void pokemonSaveData(PokemonHttp pokemonHttp) {
        try {
            pokemonHttpRepository.save(pokemonHttp);
        } catch (Exception e) {
            throw new PokemonServiceException("Error al guardar los datos de Pokemon", e);
        }
    }

    public Page<PokemonHttp> getPaginatedPokemonData(int page, int size) {
        try {
            return pokemonHttpRepository.findAll(PageRequest.of(page, size));
        } catch (Exception e) {
            throw new PokemonServiceException("Error al obtener los datos paginados de Pokemon", e);
        }
    }
}

