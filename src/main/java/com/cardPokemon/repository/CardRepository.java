package com.cardPokemon.repository;

import com.cardPokemon.models.PkmCardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<PkmCardModel, Long> {
}
