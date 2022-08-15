package com.cardPokemon.service;

import com.cardPokemon.models.PkmCardModel;
import com.cardPokemon.repository.CardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;


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

    public Page<PkmCardModel> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,30, Sort.Direction.ASC,"id");
        return repository.findAll(pageable);
    }

    public List<PkmCardModel> findRanked(String field){
        List<PkmCardModel> list = repository.findAll();
        if (field.equals("total")){
            Collections.sort(list, new TotalComparator());
            return list;
        } else if (field.equals("average")) {
            Collections.sort(list, new AverageComparator());
            return list;
        }else {
            return list;
        }

    }
}
