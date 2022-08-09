package com.cardPokemon.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "db_cards")
public class PkmCardModel {

    @Id
    @Column(name = "pkm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nationalDex;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Embedded
    @JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
    @JsonIgnoreProperties("pokemon")
    private AttributesModel attributes;

    public PkmCardModel() {
    }

    public PkmCardModel(String nationalNumber, String name, AttributesModel attributesModel){}

    public PkmCardModel(String name) {
        this.name = name;
    }

    public PkmCardModel(Long id) {this.id = id;}

    public Long getId() {return id;}

    public String getNationalDex() {
        return nationalDex;
    }

    public void setNationalDex(String nationalDex) {
        this.nationalDex = nationalDex;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

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
}
