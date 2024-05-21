package com.mok.consumoapimok.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PokemonApiResponse {

    private int count;
    private String next;
    private String previous;
    private List<PokemonData> results;

}
