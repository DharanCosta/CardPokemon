package com.cardpokemon.repository;

import com.cardpokemon.models.PkmCardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<PkmCardModel, Long> {
}
