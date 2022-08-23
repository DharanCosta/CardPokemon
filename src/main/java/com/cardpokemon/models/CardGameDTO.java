package com.cardpokemon.models;

public class CardGameDTO {
    private Long cardP1;

    private Long cardP2;

    public CardGameDTO(){}

    public CardGameDTO(Long cardP1, Long cardP2) {
        this.cardP1 = cardP1;
        this.cardP2 = cardP2;
    }

    public Long getCardP1() {
        return cardP1;
    }

    public Long getCardP2() {
        return cardP2;
    }

    public void setCardP1(Long cardP1) {
        this.cardP1 = cardP1;
    }

    public void setCardP2(Long cardP2) {
        this.cardP2 = cardP2;
    }
}
