package com.cardPokemon.controller;

import com.cardPokemon.models.CardGame;
import com.cardPokemon.models.CardGameDTO;
import com.cardPokemon.models.PkmCardModel;
import com.cardPokemon.models.ResultsDTO;
import com.cardPokemon.repository.CardGameRepository;
import com.cardPokemon.repository.CardRepository;
import com.cardPokemon.service.CardService;
import com.cardPokemon.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class FrontController {

    @Autowired
    private CardGameRepository gameRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardService cardService;

    @GetMapping("/")
    public String redirect(){
        return "redirect:/play";
    }

    @GetMapping("/play")
    public String getCardGame(Model model){
        getOnePage(model, 1);

        List<CardGame> cardGames = gameService.findAll();
        CardGame lastItem = cardGames.get(cardGames.size()-1);
        CardGameDTO cardGameDTO = new CardGameDTO();
        model.addAttribute("CardGameLastItem", lastItem);
        model.addAttribute("CardGameDTO", cardGameDTO);

        ResultsDTO resultsDTO = new ResultsDTO(gameRepository.selectTotalPlayerOne(),gameRepository.selectTotalPlayerTwo());
        model.addAttribute("resultsDTO", resultsDTO);
        return "cardgame";
    }

    @GetMapping("/play/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<CardGame> page = gameService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<CardGame> cardGames = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("cardGames", cardGames);

        List<CardGame> listGames = gameService.findAll();
        CardGame lastItem = listGames.get(listGames.size()-1);
        CardGameDTO cardGameDTO = new CardGameDTO();
        model.addAttribute("CardGameLastItem", lastItem);
        model.addAttribute("CardGameDTO", cardGameDTO);

        return "cardgame";
    }

    @PostMapping ("/play")
    public String saveNewRound(@ModelAttribute ("CardGameDTO") CardGameDTO cards){
        int playerOneScore = 0;
        int playerTwoScore = 0;
        String winner = "";
        String loser = "";
        if(cards.getCardP1() == null || cards.getCardP2() == null){
                CardGameDTO random = gameService.getRandom();
                PkmCardModel pkmCard1 = cardRepository.findById(random.getCardP1()).get();
                PkmCardModel pkmCard2 = cardRepository.findById(random.getCardP2()).get();
                CardGame newRound = gameService.getWinner(playerOneScore,playerTwoScore,random.getCardP1(),random.getCardP2(),winner,loser,pkmCard1,pkmCard2);
                gameRepository.save(newRound);
        }else{
                long playerOneCard = cards.getCardP1().longValue();
                long playerTwoCard = cards.getCardP2().longValue();
                PkmCardModel pkmCard1 = cardRepository.findById(playerOneCard).get();
                PkmCardModel pkmCard2 = cardRepository.findById(playerTwoCard).get();
                CardGame newRound = gameService.getWinner(playerOneScore,playerTwoScore,playerOneCard,playerTwoCard,winner,loser,pkmCard1,pkmCard2);
                gameRepository.save(newRound);
        }
        return "redirect:/play";
    }

    @GetMapping("/cards")
    String getPkmCards(Model  model){
        getOnePageCards(model, 1);
        return "pkmcards";
    }

    @GetMapping("/cards/page/{pageNumber}")
    public String getOnePageCards(Model model, @PathVariable("pageNumber") int currentPage){
        Page<PkmCardModel> page = cardService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<PkmCardModel> pkmCards = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("pkmCards", pkmCards);

        return "pkmcards";
    }
}
