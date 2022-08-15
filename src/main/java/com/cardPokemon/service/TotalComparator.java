package com.cardPokemon.service;

import com.cardPokemon.models.PkmCardModel;

import java.util.Comparator;

public class TotalComparator implements Comparator<PkmCardModel> {
    @Override
    public int compare(PkmCardModel o1, PkmCardModel o2) {
        return Integer.compare( o2.getTotal(), o1.getTotal());
    }
}
