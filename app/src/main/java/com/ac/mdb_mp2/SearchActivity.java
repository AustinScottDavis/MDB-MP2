package com.ac.mdb_mp2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    // vars for all UI elements
    Spinner dropdown;
    Spinner dropdown2;

    EditText minHP;
    EditText minAttack;
    EditText minDefense;
    EditText minSpecialAttack;
    EditText minSpecialDefense;
    EditText minSpeed;
    EditText minTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String pokemonData = getString(R.string.pokemonData); // fetch JSON string from file (too large to use as constant)
        Utils.parseJSON(pokemonData); // parse the data and add into allPokemon
        // access all pokemon like this: Utils.allPokemons

        // prepare dropdowns of types
        dropdown = findViewById(R.id.spinner1);
        dropdown.setPrompt("Select Type");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Utils.allTypes);
        dropdown.setAdapter(adapter);

        dropdown2 = findViewById(R.id.spinner2);
        dropdown2.setPrompt("Select Type");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Utils.allTypes);
        dropdown2.setAdapter(adapter2);

        // keep track of all textview variables
        minHP = findViewById(R.id.minHP);
        minAttack = findViewById(R.id.minAttack);
        minDefense = findViewById(R.id.minDefense);
        minSpecialAttack = findViewById(R.id.minSpecialAttack);
        minSpecialDefense = findViewById(R.id.minSpecialDefense);
        minSpeed = findViewById(R.id.minSpeed);
        minTotal = findViewById(R.id.minTotal);

    }

    // update list when filters are applied
    @Override
    public void onBackPressed() {
        System.out.println(minHP.getText());
        int minHPFilter = 0;
        int minAtkFilter = 0;
        int minDefFilter = 0;
        int minSpecAtkFilter = 0;
        int minSpecDefFilter = 0;
        int minSpeedFilter = 0;
        int minTotalFilter = 0;
        String firstType = "";
        String secondType = "";


        if (!minHP.getText().toString().isEmpty()) { minHPFilter = Integer.parseInt(minHP.getText().toString()); }
        if (!minAttack.getText().toString().isEmpty()) { minAtkFilter = Integer.parseInt(minAttack.getText().toString()); }
        if (!minDefense.getText().toString().isEmpty()) { minDefFilter = Integer.parseInt(minDefense.getText().toString()); }
        if (!minSpecialAttack.getText().toString().isEmpty()) { minSpecAtkFilter = Integer.parseInt(minSpecialAttack.getText().toString()); }
        if (!minSpecialDefense.getText().toString().isEmpty()) { minSpecDefFilter = Integer.parseInt(minSpecialDefense.getText().toString()); }
        if (!minSpeed.getText().toString().isEmpty()) { minSpeedFilter = Integer.parseInt(minSpeed.getText().toString()); }
        if (!minTotal.getText().toString().isEmpty()) { minTotalFilter = Integer.parseInt(minTotal.getText().toString()); }

        firstType = dropdown.getSelectedItem().toString();
        secondType = dropdown.getSelectedItem().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("minHP",minHPFilter);
        returnIntent.putExtra("minAttack",minAtkFilter);
        returnIntent.putExtra("minDefense",minDefFilter);
        returnIntent.putExtra("minSpecialAttack",minSpecAtkFilter);
        returnIntent.putExtra("minSpecialDefense",minSpecDefFilter);
        returnIntent.putExtra("minSpeed",minSpeedFilter);
        returnIntent.putExtra("minTotal",minTotalFilter);
        returnIntent.putExtra("firstType",firstType);
        returnIntent.putExtra("secondType",secondType);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
