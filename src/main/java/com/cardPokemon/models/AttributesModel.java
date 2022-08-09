package com.cardPokemon.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.List;

@Entity
@Embeddable
@Table(name = "db_attributes")
public class AttributesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private Long  id;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;

    public AttributesModel(){}

    public AttributesModel(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    public Long getId() {return id;}

    public int getHp() {return hp;}

    public void setHp(int hp) {this.hp = hp;}

    public int getAttack() {return attack;}

    public void setAttack(int attack) {this.attack = attack;}

    public int getDefense() {return defense;}

    public void setDefense(int defense) {this.defense = defense;}

    public int getSpecialAttack() {return specialAttack;}

    public void setSpecialAttack(int specialAttack) {this.specialAttack = specialAttack;}

    public int getSpecialDefense() {return specialDefense;}

    public void setSpecialDefense(int specialDefense) {this.specialDefense = specialDefense;}

    public int getSpeed() {return speed;}

    public void setSpeed(int speed) {this.speed = speed;}
}
