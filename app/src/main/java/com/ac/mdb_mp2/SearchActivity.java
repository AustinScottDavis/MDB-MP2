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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Utils.allTypes);
        dropdown.setAdapter(adapter);

        dropdown2 = findViewById(R.id.spinner2);
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


        if (!(Utils.minHPFilter == 0)) {minHP.setText(Integer.toString(Utils.minHPFilter)); }
        if (!(Utils.minAtkFilter == 0))   {minAttack.setText(Integer.toString(Utils.minAtkFilter)); }
        if (!(Utils.minDefFilter == 0))  {minDefense.setText(Integer.toString(Utils.minDefFilter)); }
        if (!(Utils.minSpecAtkFilter == 0))  {minSpecialAttack.setText(Integer.toString(Utils.minSpecAtkFilter)); }
        if (!(Utils.minSpecDefFilter ==0))  {minSpecialDefense.setText(Integer.toString(Utils.minSpecDefFilter)); }
        if (!(Utils.minSpeedFilter == 0))  {minSpeed.setText(Integer.toString(Utils.minSpeedFilter)); }
        if (!(Utils.minTotalFilter == 0))  {minTotal.setText(Integer.toString(Utils.minTotalFilter)); }

        if (!(Utils.firstType == "")) {dropdown.setSelection(adapter.getPosition(Utils.firstType));}
        if (!(Utils.secondType == "")) {dropdown2.setSelection(adapter2.getPosition(Utils.secondType));}
    }

    // update list when filters are applied
    @Override
    public void onBackPressed() {

        if (!minHP.getText().toString().isEmpty())             { Utils.minHPFilter = Integer.parseInt(minHP.getText().toString()); }
        if (!minAttack.getText().toString().isEmpty())         { Utils.minAtkFilter = Integer.parseInt(minAttack.getText().toString()); }
        if (!minDefense.getText().toString().isEmpty())        { Utils.minDefFilter = Integer.parseInt(minDefense.getText().toString()); }
        if (!minSpecialAttack.getText().toString().isEmpty())  { Utils.minSpecAtkFilter = Integer.parseInt(minSpecialAttack.getText().toString()); }
        if (!minSpecialDefense.getText().toString().isEmpty()) { Utils.minSpecDefFilter = Integer.parseInt(minSpecialDefense.getText().toString()); }
        if (!minSpeed.getText().toString().isEmpty())          { Utils.minSpeedFilter = Integer.parseInt(minSpeed.getText().toString()); }
        if (!minTotal.getText().toString().isEmpty())          { Utils.minTotalFilter = Integer.parseInt(minTotal.getText().toString()); }

        if (dropdown.getSelectedItem() != null)                { Utils.firstType = dropdown.getSelectedItem().toString(); }
        if (dropdown2.getSelectedItem() != null)               { Utils.secondType = dropdown2.getSelectedItem().toString(); }


        Intent returnIntent = new Intent();

        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
    
}
