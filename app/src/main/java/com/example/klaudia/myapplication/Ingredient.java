package com.example.klaudia.myapplication;

/**
 * Created by Klaudia on 2017-04-09.
 */

public class Ingredient
{
    String name;
    double amount;
    String unit;
    String originalString;
    String metaInformation;

    public Ingredient(String name, double amount, String unit, String orginalString, String metaInformation)
    {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.originalString = orginalString;
        this.metaInformation = metaInformation;
    }
}
