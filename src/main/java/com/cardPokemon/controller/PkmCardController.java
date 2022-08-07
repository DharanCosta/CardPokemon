package com.cardPokemon.controller;

import com.cardPokemon.models.PkmCardModel;
import com.cardPokemon.repository.AttributesRepository;
import com.cardPokemon.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PkmCardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AttributesRepository attributesRepository;

    @GetMapping("/all")
    public ResponseEntity<List<PkmCardModel>> getAllCards(){
        List<PkmCardModel> allCardsList = cardRepository.findAll();
        if(allCardsList.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.ok(cardRepository.findAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PkmCardModel> getById(@PathVariable Long id){
        return cardRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PkmCardModel> insertNewCard(@RequestBody PkmCardModel pkmCard){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cardRepository.save(pkmCard));
    }

    @PutMapping
    public ResponseEntity<PkmCardModel> updateCard(@RequestBody PkmCardModel pkmCard){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardRepository.save(pkmCard));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ cardRepository.deleteById(id);}

}
