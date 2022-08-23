package com.cardpokemon.controller;

import com.cardpokemon.models.PkmCardDTO;
import com.cardpokemon.models.PkmCardModel;
import com.cardpokemon.repository.AttributesRepository;
import com.cardpokemon.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PkmCardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private AttributesRepository attributesRepository;

    @GetMapping("/all")
    public ResponseEntity<List<PkmCardModel>> getAllCards(){
        List<PkmCardModel> allCardsList = cardService.findAll();
        if(allCardsList.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.ok(cardService.findAll());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<PkmCardModel> getById(@PathVariable Long id){
        return cardService.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/ranking/{field}")
    public ResponseEntity<List<PkmCardModel>> getRanked(@PathVariable String field){
        List<PkmCardModel> bestRanked = cardService.findRanked(field);
        if(bestRanked.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.ok(bestRanked);
        }
    }

    @GetMapping("/ranked")
    public Optional<Page <PkmCardModel>> getRankedAndPageable(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
            ){
        return cardService.findPage(page.get(), sortBy.get());
    }

    @PostMapping
    public ResponseEntity<PkmCardModel> insertNewCard(@RequestBody PkmCardDTO pkmCard){
        PkmCardModel pkmCardModel = new PkmCardModel(pkmCard.getNationalDex(), pkmCard.getName(), pkmCard.getTotal(), pkmCard.getAverage(), pkmCard.getAttributes());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cardService.saveNew(pkmCardModel));
    }
    @PutMapping
    public Optional<PkmCardModel> updateCard(@RequestBody PkmCardDTO pkmCard){
        return cardService.findById(pkmCard.getId()).map(
                pkmCardModel -> {
                    pkmCardModel.setNationalDex(pkmCard.getNationalDex());
                    pkmCardModel.setName(pkmCard.getName());
                    pkmCardModel.setTotal(pkmCard.getTotal());
                    pkmCardModel.setAverage(pkmCard.getAverage());
                    pkmCardModel.setAttributes(pkmCard.getAttributes());
                    return cardService.saveNew(pkmCardModel);
                });
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ cardService.deleteById(id);}

}
