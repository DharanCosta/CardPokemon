package com.cardPokemon.controller;

import com.cardPokemon.models.CardGame;
import com.cardPokemon.models.CardGameDTO;
import com.cardPokemon.models.PkmCardModel;
import com.cardPokemon.models.ResultsDTO;
import com.cardPokemon.repository.AttributesRepository;
import com.cardPokemon.repository.CardGameRepository;
import com.cardPokemon.repository.CardRepository;
import com.cardPokemon.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        List<CardGame> allGamesLists = gameRepository.findAll();
        if (allGamesLists.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(allGamesLists);
        }
    }

    @GetMapping("/scores/page/{pageNumber}")
    public ResponseEntity<Page<CardGame>> getScoresPages(@PathVariable("pageNumber") int currentPage) {
        Page<CardGame> allGamesLists = gameService.findPage(currentPage);
        return ResponseEntity.ok(allGamesLists);
    }
    @GetMapping("/scores/total")
    public ResponseEntity<ResultsDTO> getTotalScores() {
        return ResponseEntity.ok(new ResultsDTO(gameRepository.selectTotalPlayerOne(), gameRepository.selectTotalPlayerTwo()));
    }

    @PostMapping("/newround")
    public ResponseEntity<CardGame> newRound(@RequestBody CardGameDTO cards) {

        long playerOneCard = cards.getCardP1().longValue();
        long playerTwoCard = cards.getCardP2().longValue();
        int playerOneScore = 0;
        int playerTwoScore = 0;
        String winner = "";
        String loser = "";
        PkmCardModel pkmCard1 = cardRepository.findById(playerOneCard).get();
        PkmCardModel pkmCard2 = cardRepository.findById(playerTwoCard).get();
        CardGame newRound = gameService.getWinner(playerOneScore, playerTwoScore, playerOneCard, playerTwoCard, winner, loser, pkmCard1, pkmCard2);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(gameRepository.save(newRound));
    }

    @PostMapping("/random")
    public ResponseEntity<CardGame> newRandomRound(){
        CardGameDTO random = gameService.getRandom();
        PkmCardModel pkmCard1 = cardRepository.findById(random.getCardP1()).get();
        PkmCardModel pkmCard2 = cardRepository.findById(random.getCardP2()).get();
        int playerOneScore = 0;
        int playerTwoScore = 0;
        String winner = "";
        String loser = "";
        CardGame newRound = gameService.getWinner(playerOneScore, playerTwoScore, random.getCardP1(),random.getCardP2(), winner, loser, pkmCard1, pkmCard2);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(gameRepository.save(newRound));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ gameRepository.deleteById(id);}

    @DeleteMapping ("/delete/all")
    public void deleteAll(){ gameRepository.deleteAll();}

}
