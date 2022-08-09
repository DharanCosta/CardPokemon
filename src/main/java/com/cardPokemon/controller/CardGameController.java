package com.cardPokemon.controller;

import com.cardPokemon.models.CardGame;
import com.cardPokemon.models.PkmCardModel;
import com.cardPokemon.repository.AttributesRepository;
import com.cardPokemon.repository.CardGameRepository;
import com.cardPokemon.repository.CardRepository;

import com.cardPokemon.service.GameService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/play")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CardGameController {

    @Autowired
    private CardGameRepository gameRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private GameService gameService;

    @Autowired
    private AttributesRepository attributesRepository;

    @GetMapping("/scores")
    public ResponseEntity<List<CardGame>> getScores() {
        List<CardGame>  allGamesLists = gameRepository.findAll();
        if(allGamesLists.isEmpty()) {
            return ResponseEntity.status(204).build();
        }else {
            return ResponseEntity.ok(allGamesLists);
        }
    }

    @GetMapping("/scores/total")
    public ResponseEntity<String> getTotalScores(){
        int totalPlayerOne = gameRepository.selectTotalPlayerOne();
        int totalPlayerTwo = gameRepository.selectTotalPlayerTwo();
        return ResponseEntity.ok("{\n Total Player 1: "+totalPlayerOne+
                "\n Total Player 2: "+totalPlayerTwo+"\n }");
    }


    @PostMapping
    public ResponseEntity<CardGame> newRound(@RequestBody ObjectNode cards){

        long playerOneCard = cards.get("p1Card").longValue();
        long playerTwoCard = cards.get("p2Card").longValue();
        int playerOneScore = 0;
        int playerTwoScore = 0;
        String winner = "";
        String loser = "";
        PkmCardModel pkmCard1 = cardRepository.findById(playerOneCard).get();
        PkmCardModel pkmCard2 = cardRepository.findById(playerTwoCard).get();
        CardGame newRound = gameService.getWinner(playerOneScore,playerTwoScore,playerOneCard,playerTwoCard,winner,loser,pkmCard1,pkmCard2);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(gameRepository.save(newRound));
    }




}
