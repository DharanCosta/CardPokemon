package com.cardpokemon.models;

public class ResultsDTO {

    int totalP1;
    int totalP2;

    public ResultsDTO(int totalP1, int totalP2) {
        this.totalP1 = totalP1;
        this.totalP2 = totalP2;
    }

    public int getTotalP1() {
        return totalP1;
    }

    public void setTotalP1(int totalP1) {
        this.totalP1 = totalP1;
    }

    public int getTotalP2() {
        return totalP2;
    }

    public void setTotalP2(int totalP2) {
        this.totalP2 = totalP2;
    }
}
