package com.cardPokemon.service;

import com.cardPokemon.models.AttributesModel;
import com.cardPokemon.models.PkmCardDTO;
import com.cardPokemon.models.PkmCardModel;
import com.cardPokemon.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public Iterable<PkmCardModel> list() {
        return repository.findAll();
    }

    public Iterable<PkmCardModel> save(List<PkmCardModel> cards) {
        return repository.saveAll(cards);
    }
}
