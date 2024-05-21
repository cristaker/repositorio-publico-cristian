package com.mok.consumoapimok.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PokemonData {

    private String name;
    private String url;
    private int height;
    private int weight;
}
