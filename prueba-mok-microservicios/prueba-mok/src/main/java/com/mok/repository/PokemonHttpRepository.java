package com.mok.repository;

import com.mok.models.PokemonHttp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonHttpRepository extends JpaRepository<PokemonHttp, Long> {
}
