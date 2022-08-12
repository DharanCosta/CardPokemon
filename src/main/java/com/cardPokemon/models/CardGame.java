package com.cardPokemon.models;

import javax.persistence.*;

@Entity
@Table(name= "db_card_game")
public class CardGame {
    @Id
    @Column(name = "round")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int scoreP1;

    private int scoreP2;

    private Long cardP1;

    private Long cardP2;

    private String winner;

    private String loser;

    @ManyToOne
    private PkmCardModel pkmCardPlayer1;
    @ManyToOne
    private PkmCardModel pkmCardPlayer2;

    public CardGame() {}

    public CardGame(int playerOneScore, int playerTwoScore, Long playerOneCard, Long playerTwoCard, String winner, String loser, PkmCardModel pkmCardPlayer1, PkmCardModel pkmCardPlayer2) {
        this.scoreP1 = playerOneScore;
        this.scoreP2 = playerTwoScore;
        this.cardP1 = playerOneCard;
        this.cardP2 = playerTwoCard;
        this.winner = winner;
        this.loser = loser;
        this.pkmCardPlayer1 = pkmCardPlayer1;
        this.pkmCardPlayer2 = pkmCardPlayer2;
    }

    public Long getId() {
        return id;
    }

    public int getScoreP1() {
        return scoreP1;
    }

    public void setScoreP1(int scoreP1) {
        this.scoreP1 = scoreP1;
    }

    public int getScoreP2() {
        return scoreP2;
    }

    public void setScoreP2(int scoreP2) {
        this.scoreP2 = scoreP2;
    }

    public Long getCardP1() {
        return cardP1;
    }

    public void setCardP1(Long cardP1) {
        this.cardP1 = cardP1;
    }

    public Long getCardP2() {
        return cardP2;
    }

    public void setCardP2(Long cardP2) {
        this.cardP2 = cardP2;
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
