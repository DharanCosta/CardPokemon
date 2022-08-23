package com.cardpokemon.service;

import com.cardpokemon.models.CardGame;
import com.cardpokemon.models.CardGameDTO;
import com.cardpokemon.models.PkmCardModel;
import com.cardpokemon.repository.AttributesRepository;
import com.cardpokemon.repository.CardGameRepository;
import com.cardpokemon.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class GameService {

    @Autowired
    private CardGameRepository gameRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AttributesRepository attributesRepository;

    private Random random = new Random();

    public List<CardGame> findAll(){
        return gameRepository.findAll();
    }

    public Optional<PkmCardModel> findCardById(Long id){
        if(cardRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException();
        }
        return cardRepository.findById(id);
    }
    public Page<CardGame> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,20, Sort.Direction.DESC,"id");
        return gameRepository.findAll(pageable);
    }

    public int getTotalResults1(){
        return gameRepository.selectTotalPlayerOne();
    }
    public int getTotalResults2(){
        return gameRepository.selectTotalPlayerTwo();
    }

    public CardGame getWinner(Long playerOneCard, Long playerTwoCard) {
        int[] wins ={0,0};
        PkmCardModel pkmCard1 = findCardById(playerOneCard).orElseThrow();
        PkmCardModel pkmCard2 = findCardById(playerTwoCard).orElseThrow();
        compareAttribute(pkmCard1.getAttributes().getHp(), pkmCard2.getAttributes().getHp(), wins);
        compareAttribute(pkmCard1.getAttributes().getAttack(), pkmCard2.getAttributes().getAttack(), wins);
        compareAttribute(pkmCard1.getAttributes().getDefense(), pkmCard2.getAttributes().getDefense(),wins);
        compareAttribute(pkmCard1.getAttributes().getSpecialAttack(), pkmCard2.getAttributes().getSpecialAttack(), wins);
        compareAttribute(pkmCard1.getAttributes().getSpecialDefense(), pkmCard2.getAttributes().getSpecialDefense(),wins);
        compareAttribute(pkmCard1.getAttributes().getSpeed(), pkmCard2.getAttributes().getSpeed(), wins);

        if ( wins[0] == wins[1]) {
                return new CardGame(0, 0, playerOneCard, playerTwoCard, "That's a tie!", "No Loser", pkmCard1,pkmCard2);
        } else if (wins[0] > wins[1]) {
            return new CardGame(1, 0, playerOneCard, playerTwoCard, "Player ONE wins!", "Player TWO loses", pkmCard1,pkmCard2);
        } else {
            return new CardGame(0, 1, playerOneCard, playerTwoCard, "Player TWO wins!", "Player ONE loses", pkmCard1,pkmCard2);
        }
    }

    public static int[] compareAttribute(int p1, int p2, int[] wins){
        if(p1 > p2){
            wins[0]++;
        } else if(p1 < p2){
            wins[1]++;
        }else{
            wins[0]++;
            wins[1]++;
        }
        return wins;
    }

    public CardGameDTO getRandom(){
        Long p1 = random.nextLong(cardRepository.findAll().size());
        Long p2 = random.nextLong(cardRepository.findAll().size());

        return new CardGameDTO(p1,p2);
    }

    public CardGame saveNew(CardGame newRound){
        return gameRepository.save(newRound);
    }

    public void deleteById(Long id){
       gameRepository.deleteById(id);
    }
    public void deleteAll(){
        gameRepository.deleteAll();
    }
}


