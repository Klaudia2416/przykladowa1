package com.example.klaudia.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import java.util.ArrayList;
import org.json.*;



public class MainActivity extends AppCompatActivity
{
    private final String TAG = MainActivity.class.getSimpleName();
    public String link = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/products/classify";
    Button send;
    EditText number;
    EditText tags;
    ListView list;
    Recipe [] recipes;
    ArrayList<String> adapterList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = (Button) findViewById(R.id.send);
        number = (EditText) findViewById(R.id.number);
        tags = (EditText) findViewById(R.id.tags);
        list = (ListView) findViewById(R.id.listView);

        plapplapla

                
        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String numberText = number.getText().toString();
                String tagsText = tags.getText().toString();
                tagsText = tagsText.replace(",", "%2C");
                final String request = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=true&number=" +
                        numberText + "&tags=" + tagsText;

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
                            response = Unirest.get(request)
                                    .header("X-Mashape-Key", "OXOqW68hHLmshp14m3QjfcQLuoqop1WP587jsnETvuhAoakUUI")
                                    .header("Accept", "application/json")
                                    .asJson();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return response;
                    }

                    protected void onPostExecute(HttpResponse<JsonNode> response)
                    {
                        try
                        {
                            JSONObject object = response.getBody().getObject();
                            JSONArray array = object.getJSONArray("recipes");
                            recipes = new Recipe[array.length()];

                            for (int i = 0; i < array.length(); i++)
                            {
                                Recipe recipe = new Recipe(array.getJSONObject(i));
                                recipes[i] = recipe;
                                adapterList.add(recipe.title);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, adapterList);
                            list.setAdapter(adapter);

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }

                }.execute();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent intent = new Intent(getApplicationContext(), RecipeList.class);
                Bundle bundle = new Bundle();
                bundle.putInt("index", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }




}



