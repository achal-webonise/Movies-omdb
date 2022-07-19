package com.example.movies_omdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        movieList = new ArrayList<>();
        fetchMovies1();


    }
    private void fetchMovies1() {

        String url = "https://www.omdbapi.com/?apikey=ea9dc777&s=war";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
               //     Log.d("API", "onResponse: "+response.getString("Title"));
                    JSONArray Search = response.optJSONArray("Search");
                    for(int i=0;i<Search.length();i++)
                    {
                        Log.d("TAG", "onResponse: "+Search.getJSONObject(i).getString("Title"));
                        Movie movie = new Movie(Search.getJSONObject(i).getString("Title") , "" , "" , "");
                        movieList.add(movie);
                    }
//                    String Year = response.getString("Year");
//                    String Rated  = response.getString("Rated");
//                    String Released = response.getString("Released");



                    MovieAdapter adapter = new MovieAdapter(MainActivity.this , movieList);

                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    Log.d("API", e.toString());

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API", error.toString());

            }
        });
        requestQueue.add(jsonObjectRequest);


    }
//    private void fetchMovies() {
//
//        String url = "http://www.omdbapi.com/?apikey=ea9dc777&t=Scooby-Doo";
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        for (int i = 0 ; i < response.length() ; i ++){
//                            try {
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String title = jsonObject.getString("Title");
//                                String Year = jsonObject.getString("Year");
//                                String Rated  = jsonObject.getString("Rated");
//                                String Released = jsonObject.getDouble("Released");
//
//                                Movie movie = new Movie(title , Rated , Year , Released);
//                                movieList.add(movie);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            MovieAdapter adapter = new MovieAdapter(MainActivity.this , movieList);
//
//                            recyclerView.setAdapter(adapter);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        requestQueue.add(jsonArrayRequest);
//    }
}