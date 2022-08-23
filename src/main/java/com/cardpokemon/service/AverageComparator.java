package com.cardpokemon.service;

import com.cardpokemon.models.PkmCardModel;

import java.util.Comparator;

public class AverageComparator implements Comparator<PkmCardModel> {
    @Override
    public int compare(PkmCardModel o1, PkmCardModel o2) {
        return  Float.compare( o2.getAverage(),o1.getAverage());
    }
}
