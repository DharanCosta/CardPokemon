package com.cardPokemon.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "db_cards")
public class PkmCardModel {

    @Id
    @Column(name = "pkm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
    @JsonIgnoreProperties("pokemon")
    private AttributesModel attributes;

    public PkmCardModel(){}

    public PkmCardModel(String name) {
        this.name = name;
    }

    public PkmCardModel(Long id) {this.id = id;}

    public Long getId() {return id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public AttributesModel getAttributes() {return attributes;}

    public void setAttributes(AttributesModel attributes) {this.attributes = attributes;}

}
