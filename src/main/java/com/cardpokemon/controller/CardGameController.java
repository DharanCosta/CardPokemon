package com.cardpokemon.controller;

import com.cardpokemon.models.CardGame;
import com.cardpokemon.models.CardGameDTO;
import com.cardpokemon.models.ResultsDTO;
import com.cardpokemon.service.GameService;

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
    private GameService gameService;

    @GetMapping("/scores")
    public ResponseEntity<List<CardGame>> getScores() {
        List<CardGame> allGamesLists = gameService.findAll();
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
        return ResponseEntity.ok(new ResultsDTO(gameService.getTotalResults1(), gameService.getTotalResults2()));
    }

    @PostMapping("/newround")
    public ResponseEntity<CardGame> newRound(@RequestBody CardGameDTO cards) {

        long playerOneCard = cards.getCardP1().longValue();
        long playerTwoCard = cards.getCardP2().longValue();
        CardGame newRound = gameService.getWinner(playerOneCard, playerTwoCard);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(gameService.saveNew(newRound));
    }

    @PostMapping("/random")
    public ResponseEntity<CardGame> newRandomRound(){
        CardGameDTO random = gameService.getRandom();
        CardGame newRound = gameService.getWinner(random.getCardP1(),random.getCardP2());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(gameService.saveNew(newRound));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ gameService.deleteById(id);}

    @DeleteMapping ("/delete/all")
    public void deleteAll(){ gameService.deleteAll();}

}
