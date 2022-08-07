package com.cardPokemon.service;

import com.cardPokemon.models.PkmCardModel;
import com.cardPokemon.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;

    public Page<PkmCardModel> findCards(Integer minCards, Integer maxCards, Pageable pageable){

//        Integer default = 15;

        return null;
    }

}
