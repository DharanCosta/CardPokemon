package com.cardPokemon.models;

import javax.persistence.*;

@Entity
@Table(name= "db_card_game")
public class CardGame {
    @Id
    @Column(name = "round")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int playerOneScore = 0;

    private int playerTwoScore = 0;

//    private int playerOneCard = (int) Math.random() * 1061;
    private Long playerOneCard;
    private Long playerTwoCard;

    private String winner;

    private String loser;

    @ManyToOne
    private PkmCardModel pkmCardPlayer1;
    @ManyToOne
    private PkmCardModel pkmCardPlayer2;

    public CardGame() {}

    public CardGame(int playerOneScore, int playerTwoScore, Long playerOneCard, Long playerTwoCard, String winner, String loser, PkmCardModel pkmCardPlayer1, PkmCardModel pkmCardPlayer2) {
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
        this.playerOneCard = playerOneCard;
        this.playerTwoCard = playerTwoCard;
        this.winner = winner;
        this.loser = loser;
        this.pkmCardPlayer1 = pkmCardPlayer1;
        this.pkmCardPlayer2 = pkmCardPlayer2;
    }

    public Long getId() {
        return id;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(int playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerTwoScore(int playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    public Long getPlayerOneCard() {
        return playerOneCard;
    }

    public void setPlayerOneCard(Long playerOneCard) {
        this.playerOneCard = playerOneCard;
    }

    public Long getPlayerTwoCard() {
        return playerTwoCard;
    }

    public void setPlayerTwoCard(Long playerTwoCard) {
        this.playerTwoCard = playerTwoCard;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }

    public PkmCardModel getPkmCardPlayer1() {
        return pkmCardPlayer1;
    }

    public void setPkmCardPlayer1(PkmCardModel pkmCardPlayer1) {
        this.pkmCardPlayer1 = pkmCardPlayer1;
    }

    public PkmCardModel getPkmCardPlayer2() {
        return pkmCardPlayer2;
    }

    public void setPkmCardPlayer2(PkmCardModel pkmCardPlayer2) {
        this.pkmCardPlayer2 = pkmCardPlayer2;
    }
}
