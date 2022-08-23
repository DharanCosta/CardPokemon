package com.cardpokemon.repository;

import com.cardpokemon.models.CardGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardGameRepository extends JpaRepository<CardGame, Long> {
    @Query(value = "SELECT SUM(scoreP1) FROM db_card_game", nativeQuery = true)
    Integer selectTotalPlayerOne();
    @Query(value = "SELECT SUM(scoreP2) FROM db_card_game", nativeQuery = true)
    Integer selectTotalPlayerTwo();
}
