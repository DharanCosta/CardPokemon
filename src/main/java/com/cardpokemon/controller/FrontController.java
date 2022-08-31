package com.cardpokemon.controller;

import com.cardpokemon.models.CardGame;
import com.cardpokemon.models.CardGameDTO;
import com.cardpokemon.models.PkmCardModel;
import com.cardpokemon.models.ResultsDTO;
import com.cardpokemon.service.CardService;
import com.cardpokemon.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class FrontController {

    @Autowired
    private GameService gameService;
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

        ResultsDTO resultsDTO = new ResultsDTO(gameService.getTotalResults1(),gameService.getTotalResults2());
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
        model.addAttribute("CardGameLastItem", lastItem);
        CardGameDTO cardGameDTO = new CardGameDTO();
        model.addAttribute("CardGameDTO", cardGameDTO);

        ResultsDTO resultsDTO = new ResultsDTO(gameService.getTotalResults1(),gameService.getTotalResults2());
        model.addAttribute("resultsDTO", resultsDTO);

        return "cardgame";
    }

    @PostMapping ("/play")
    public String saveNewRound(@ModelAttribute ("CardGameDTO") CardGameDTO cards){
        if(cards.getCardP1() == null || cards.getCardP2() == null){
                CardGameDTO random = gameService.getRandom();
                CardGame newRound = gameService.getWinner(random.getCardP1(),random.getCardP2());
                gameService.saveNew(newRound);
        }else{
                long playerOneCard = cards.getCardP1().longValue();
                long playerTwoCard = cards.getCardP2().longValue();
                CardGame newRound = gameService.getWinner(playerOneCard,playerTwoCard);
                gameService.saveNew(newRound);
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
        Optional<Page<PkmCardModel>> page = cardService.findAllPage(currentPage);
        int totalPages = page.get().getTotalPages();
        long totalItems = page.get().getTotalElements();
        List<PkmCardModel> pkmCards = page.get().getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("pkmCards", pkmCards);

        return "pkmcards";
    }
}
