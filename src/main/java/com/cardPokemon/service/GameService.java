package com.cardPokemon.service;

import com.cardPokemon.models.CardGame;
import com.cardPokemon.models.CardGameDTO;
import com.cardPokemon.models.PkmCardModel;
import com.cardPokemon.repository.AttributesRepository;
import com.cardPokemon.repository.CardGameRepository;
import com.cardPokemon.repository.CardRepository;
import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    @Autowired
    private CardGameRepository gameRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AttributesRepository attributesRepository;

    public List<CardGame> findAll(){
        return gameRepository.findAll();
    }
    public Page<CardGame> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,10, Sort.Direction.DESC,"id");
        return gameRepository.findAll(pageable);
    }

    public CardGame getWinner(int playerOneScore, int playerTwoScore, Long playerOneCard, Long playerTwoCard, String winner,String loser, PkmCardModel pkmCard1, PkmCardModel pkmCard2) {
            if (cardRepository.findById(playerOneCard).get().getTotal() == cardRepository.findById(playerTwoCard).get().getTotal()) {
                    playerOneScore = 0;
                    playerTwoScore = 0;
                    winner = "That's a tie!";
                    loser = "No Loser";
                    pkmCard1 = cardRepository.findById(playerOneCard).get();
                    pkmCard2 = cardRepository.findById(playerTwoCard).get();
                    return new CardGame(playerOneScore, playerTwoScore, playerOneCard, playerTwoCard, winner, loser, pkmCard1,pkmCard2);
            } else if (cardRepository.findById(playerOneCard).get().getTotal() > cardRepository.findById(playerTwoCard).get().getTotal()) {
                playerOneScore = 1;
                playerTwoScore = 0;
                winner = "Player ONE wins!";
                loser = "Player TWO loses";
                pkmCard1 = cardRepository.findById(playerOneCard).get();
                pkmCard2 = cardRepository.findById(playerTwoCard).get();
                return new CardGame(playerOneScore, playerTwoScore, playerOneCard, playerTwoCard, winner, loser, pkmCard1,pkmCard2);
            } else {
                playerOneScore = 0;
                playerTwoScore = 1;
                winner = "Player TWO wins!";
                loser = "Player ONE loses";
                pkmCard1 = cardRepository.findById(playerOneCard).get();
                pkmCard2 = cardRepository.findById(playerTwoCard).get();
                return new CardGame(playerOneScore, playerTwoScore, playerOneCard, playerTwoCard, winner, loser, pkmCard1,pkmCard2);
            }
    }

    public CardGameDTO getRandom(){
        Random random = new Random();
        Long p1 = random.nextLong(cardRepository.findAll().size());
        Long p2 = random.nextLong(cardRepository.findAll().size());
        return new CardGameDTO(p1,p2);

    }

}


