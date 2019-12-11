package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner CountriesSpinner;
    private Spinner CitiesSpinner;
    private Spinner HouseNumberSpinner;
    private Button ShowAddressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews() {
        CountriesSpinner = findViewById(R.id.countriesSpinner);
        CitiesSpinner = findViewById(R.id.citiesSpinner);
        HouseNumberSpinner = findViewById(R.id.houseNumberSpinner);
        ShowAddressBtn = findViewById(R.id.showAddress);
        initSpinnerCountries();
        initHousNumbersSpinner();
        ShowsBtn();
    }

    private void initHousNumbersSpinner() {
        Integer[] houseNumbers = new Integer[50];
        for (int i = 1, length = houseNumbers.length; i <= length; i++) {
            houseNumbers[i - 1] = i;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, houseNumbers);
        HouseNumberSpinner.setAdapter(adapter);
    }

    private void initSpinnerCountries() {
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CountriesSpinner.setAdapter(adapterCountries);

        CountriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] countries = getResources().getStringArray(R.array.countries);
                initSpinnerCities(countries[i]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initSpinnerCities(String country) {
        ArrayAdapter<CharSequence> adapter = null;
        switch (country) {
            case "Россия":
                adapter = ArrayAdapter.createFromResource(this, R.array.r_cities, android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapter = ArrayAdapter.createFromResource(this, R.array.u_cities, android.R.layout.simple_spinner_item);
                break;
            case "Белоруссия":
                adapter = ArrayAdapter.createFromResource(this, R.array.b_cities, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            CitiesSpinner.setAdapter(adapter);
        }
    }

    private void ShowsBtn(){
        ShowAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this
                        ,CountriesSpinner.getSelectedItem().toString()
                                + " "
                                + CitiesSpinner.getSelectedItem().toString()
                                + " "
                                + HouseNumberSpinner.getSelectedItem().toString()
                        ,Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}