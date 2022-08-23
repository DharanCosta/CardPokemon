package com.cardpokemon.service;

import com.cardpokemon.models.PkmCardModel;
import com.cardpokemon.repository.CardRepository;

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

    public List<PkmCardModel> findAll(){ return repository.findAll(); }
    public Iterable<PkmCardModel> list() {
        return repository.findAll();
    }

    public Optional<PkmCardModel> findById(Long id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id);
        }
        else {
            return Optional.empty();
        }
    }
    public Iterable<PkmCardModel> save(List<PkmCardModel> cards) {
        return repository.saveAll(cards);
    }

    public Optional<Page<PkmCardModel>> findPage(int pageNumber, String sortBy){
        Pageable pageable = PageRequest.of(pageNumber -1,30, Sort.Direction.DESC, sortBy,"total");
        return Optional.of(repository.findAll(pageable));
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
    public PkmCardModel saveNew(PkmCardModel newCard){
        return repository.save(newCard);
    }


    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
