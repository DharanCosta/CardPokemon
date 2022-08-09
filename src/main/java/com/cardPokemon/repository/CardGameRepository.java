package com.cardPokemon.repository;

import com.cardPokemon.models.CardGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardGameRepository extends JpaRepository<CardGame, Long> {
    @Query(value = "SELECT SUM(player_one_score) FROM db_card_game", nativeQuery = true)
    Integer selectTotalPlayerOne();
    @Query(value = "SELECT SUM(player_two_score) FROM db_card_game", nativeQuery = true)
    Integer selectTotalPlayerTwo();
}
