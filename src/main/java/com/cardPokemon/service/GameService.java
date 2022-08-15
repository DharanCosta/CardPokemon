package com.cardPokemon.service;

import com.cardPokemon.models.CardGame;
import com.cardPokemon.models.CardGameDTO;
import com.cardPokemon.models.PkmCardModel;
import com.cardPokemon.repository.AttributesRepository;
import com.cardPokemon.repository.CardGameRepository;
import com.cardPokemon.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameService {

    @Autowired
    private CardGameRepository gameRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AttributesRepository attributesRepository;

    public List<CardGame> findAll(){
        return gameRepository.findAll();
    }
    public Page<CardGame> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,20, Sort.Direction.DESC,"id");
        return gameRepository.findAll(pageable);
    }

    public CardGame getWinner(int playerOneScore, int playerTwoScore, Long playerOneCard, Long playerTwoCard, String winner,String loser, PkmCardModel pkmCard1, PkmCardModel pkmCard2) {
        int winsP1 =0;
        int winsP2 =0;
        if(pkmCard1.getAttributes().getHp()>pkmCard2.getAttributes().getHp()){winsP1++;
        }else if(pkmCard1.getAttributes().getHp()<pkmCard2.getAttributes().getHp()){winsP2++;
        }else{winsP1++;winsP2++;}

        if(pkmCard1.getAttributes().getAttack()>pkmCard2.getAttributes().getAttack()){winsP1++;
        }else if(pkmCard1.getAttributes().getAttack()<pkmCard2.getAttributes().getAttack()){winsP2++;
        }else{winsP1++;winsP2++;}

        if(pkmCard1.getAttributes().getDefense()>pkmCard2.getAttributes().getDefense()){winsP1++;
        }else if(pkmCard1.getAttributes().getDefense()<pkmCard2.getAttributes().getDefense()){winsP2++;
        }else{winsP1++;winsP2++;}

        if(pkmCard1.getAttributes().getSpecialAttack()>pkmCard2.getAttributes().getSpecialAttack()){winsP1++;
        }else if(pkmCard1.getAttributes().getSpecialAttack()<pkmCard2.getAttributes().getSpecialAttack()){winsP2++;
        }else{winsP1++;winsP2++;}

        if(pkmCard1.getAttributes().getSpecialDefense()>pkmCard2.getAttributes().getSpecialDefense()){winsP1++;
        }else if(pkmCard1.getAttributes().getSpecialDefense()<pkmCard2.getAttributes().getSpecialDefense()){winsP2++;
        }else{winsP1++;winsP2++;}

        if(pkmCard1.getAttributes().getSpeed()>pkmCard2.getAttributes().getSpeed()){winsP1++;
        }else if(pkmCard1.getAttributes().getSpeed()<pkmCard2.getAttributes().getSpeed()){winsP2++;
        }else{winsP1++;winsP2++;}

        System.out.println(winsP1 +" x "+winsP2 );

        if ( winsP1 == winsP2) {
                    playerOneScore = 0;
                    playerTwoScore = 0;
                    winner = "That's a tie!";
                    loser = "No Loser";
                    pkmCard1 = cardRepository.findById(playerOneCard).get();
                    pkmCard2 = cardRepository.findById(playerTwoCard).get();
                    return new CardGame(playerOneScore, playerTwoScore, playerOneCard, playerTwoCard, winner, loser, pkmCard1,pkmCard2);
            } else if (winsP1 > winsP2) {
                playerOneScore = 1;
                playerTwoScore = 0;
                winner = "Player ONE wins!";
                loser = "Player TWO loses";
                pkmCard1 = cardRepository.findById(playerOneCard).get();
                pkmCard2 = cardRepository.findById(playerTwoCard).get();
                return new CardGame(playerOneScore, playerTwoScore, playerOneCard, playerTwoCard, winner, loser, pkmCard1,pkmCard2);
            } else {
                playerOneScore = 0;
                playerTwoScore = 1;
                winner = "Player TWO wins!";
                loser = "Player ONE loses";
                pkmCard1 = cardRepository.findById(playerOneCard).get();
                pkmCard2 = cardRepository.findById(playerTwoCard).get();
                return new CardGame(playerOneScore, playerTwoScore, playerOneCard, playerTwoCard, winner, loser, pkmCard1,pkmCard2);
            }
    }

    public CardGameDTO getRandom(){
        Random random = new Random();
        Long p1 = random.nextLong(cardRepository.findAll().size());
        Long p2 = random.nextLong(cardRepository.findAll().size());
        return new CardGameDTO(p1,p2);

    }


}


