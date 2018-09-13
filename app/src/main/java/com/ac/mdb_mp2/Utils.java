package com.ac.mdb_mp2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utils {

    public static ArrayList<Pokemon> allPokemon = new ArrayList<Pokemon>();
    public static ArrayList<String> allTypes = new ArrayList<String>();

    public static int minHPFilter = 0;
    public static int minAtkFilter = 0;
    public static int minDefFilter = 0;
    public static int minSpecAtkFilter = 0;
    public static int minSpecDefFilter = 0;
    public static int minSpeedFilter = 0;
    public static int minTotalFilter = 0;
    public static String firstType = "";
    public static String secondType = "";


    public static void parseJSON(String pokemonData) {
        JSONObject obj = null; // holds all data
        try {
            obj = new JSONObject(pokemonData); // create as JSON
            Iterator<String> pokemonNames = obj.keys();
            while (pokemonNames.hasNext()) { // loop through all keys
                String currentPokemon = pokemonNames.next(); // name of current pokemon

                JSONObject pokemonInfo = obj.getJSONObject(currentPokemon); // info for this pokemon
                int num = Integer.parseInt(pokemonInfo.getString("#"));
                int attack = Integer.parseInt(pokemonInfo.getString("Attack"));
                int defense = Integer.parseInt(pokemonInfo.getString("Defense"));
                int hp = Integer.parseInt(pokemonInfo.getString("HP"));
                int specialAttack = Integer.parseInt(pokemonInfo.getString("Sp. Atk"));
                int specialDefense = Integer.parseInt(pokemonInfo.getString("Sp. Def"));
                int speed = Integer.parseInt(pokemonInfo.getString("Speed"));
                int total = Integer.parseInt(pokemonInfo.getString("Total"));

                // add type
                JSONArray arr = pokemonInfo.getJSONArray("Type");
                ArrayList<String> type = new ArrayList<String>();
                for (int i = 0; i < arr.length(); i++) {
                    String currentType = arr.getString(i);
                    type.add(currentType);
                    // save type in lists of all types (easier to search)
                    if (!allTypes.contains(currentType)) {
                        allTypes.add(currentType);
                    }
                }

                String flavorText = pokemonInfo.getString("FlavorText");
                String species = pokemonInfo.getString("Species");

                Pokemon current = new Pokemon(currentPokemon, num, attack, defense, hp, specialAttack, specialDefense, speed, total, type, flavorText, species);
                allPokemon.add(current);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
