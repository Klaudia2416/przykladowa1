package com.example.klaudia.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RecipesFragment extends android.app.Fragment
{
    ListView lv;
    Button recipeButton;
    EditText caloriesEt;
    String calories;
    String username;
    String keywordCalories = "calories";
    String url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/4632/summary";
    EditText name;
    public static final String TAG = RecipesFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        name = (EditText) view.findViewById(R.id.editText);
      //  lv = (ListView) view.findViewById(R.id.list);
        recipeButton = (Button) view.findViewById(R.id.find_recipes_button);
        caloriesEt = (EditText) view.findViewById(R.id.calories_for_recipe);

        recipeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                calories = caloriesEt.getText().toString();
                //username = ParseUser.getCurrentUser().getUsername();
    //            url = url + calories;
       //         Log.d(TAG, "Max calories are : " + calories);
      //          Log.d(TAG, "URL is : " + url);



                new AsyncTask<Void, Void, HttpResponse<JsonNode>>()
                {

                    @Override
                    protected void onPreExecute() {

                    }

                    @Override
                    protected HttpResponse<JsonNode> doInBackground(Void... params)
                    {
                        HttpResponse<JsonNode> response = null;
                        try {
                            response = Unirest.post("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/products/classify")
                                    .header("X-Mashape-Key", "OXOqW68hHLmshp14m3QjfcQLuoqop1WP587jsnETvuhAoakUUI")
                                    .header("Content-Type", "application/json")
                                    .header("Accept", "application/json")
                                    .body("{\"title\":\"Kroger Vitamin A & D Reduced Fat 2% Milk\",\"upc\":\"\",\"plu_code\":\"\"}")
                                    .asJson();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return response;
                    }

                    protected void onPostExecute(HttpResponse<JsonNode> response)
                    {
                        String answer = response.getBody().toString();
                        caloriesEt.setText(answer);
                    }

                }.execute();
            }
        });
        return view;
    }
}