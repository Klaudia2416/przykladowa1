package com.example.klaudia.myapplication;

import com.mashape.unirest.http.JsonNode;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Klaudia on 2017-04-09.
 */

public class Recipe
{
    String title;
    int readyInMinutes;
    String instructions;
    ArrayList<Ingredient> ingredients;
    JSONArray jsonArray;
    JSONObject json;


    public Recipe(JSONObject json) throws JSONException
    {
        this.json = json;
        title = json.getString("title");
        readyInMinutes = json.getInt("readyInMinutes");
        jsonArray = json.getJSONArray("extendedIngredients");
        ingredients = new ArrayList<Ingredient>(jsonArray.length());
        instructions = json.getString("instructions");

        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject ingredient = jsonArray.getJSONObject(i);
            String name = ingredient.getString("name");
            double amount = ingredient.getDouble("amount");
            String unit = ingredient.getString("unit");
            String originalString = ingredient.getString("originalString");
            String meta = ingredient.getString("metaInformation");

            Ingredient tmp = new Ingredient(name, amount, unit, originalString, meta);
            ingredients.add(tmp);
        }
    }


    public void show()
    {

    }
}
