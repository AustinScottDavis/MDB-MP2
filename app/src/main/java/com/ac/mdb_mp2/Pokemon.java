package com.ac.mdb_mp2;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {
    String name;
    int number;
    int attack;
    int defense;
    int hp;
    int spAttack;
    int spDefense;
    int speed;
    int total;
    ArrayList<String> type;

    String flavorText;
    String species;

    public Pokemon(String name, int num, int atk, int def, int hp, int spAtk, int spDef, int spd, int total, ArrayList<String> type, String flavorText, String species) {
        this.name = name;
        this.number = num;
        this.attack = atk;
        this.defense = def;
        this.hp = hp;
        this.spAttack = spAtk;
        this.spDefense = spDef;
        this.speed = spd;
        this.total = total;
        this.type = type;
        this.flavorText = flavorText;
        this.species = species;
    }

}
