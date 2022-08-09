package com.cardPokemon.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collections;
import java.util.Comparator;

@Entity
@Table(name = "db_cards")
public class PkmCardModel implements Comparable<PkmCardModel> {

    @Id
    @Column(name = "pkm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nationalDex;

    private String name;

    private int total;

    private float average;
    @OneToOne(cascade = CascadeType.ALL)
    @Embedded
    @JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
    @JsonIgnoreProperties("pokemon")
    private AttributesModel attributes;

    public PkmCardModel() {
    }

    public PkmCardModel(String nationalDex, String name, int total, float average, AttributesModel attributes) {
        this.nationalDex = nationalDex;
        this.name = name;
        this.total = total;
        this.average = average;
        this.attributes = attributes;
    }

    public Long getId() {return id;}

    public String getNationalDex() {
        return nationalDex;
    }

    public void setNationalDex(String nationalDex) {
        this.nationalDex = nationalDex;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public AttributesModel getAttributes() {return attributes;}

    public void setAttributes(AttributesModel attributes) {this.attributes = attributes;}


    @Override
    public String toString() {
        return "PkmCardModel{" +
                "id=" + id +
                ", nationalDex=" + nationalDex +
                ", name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    @Override
    public int compareTo(PkmCardModel pkm1) {
        return Integer.compare(pkm1.getTotal(), getTotal());
    }
}
