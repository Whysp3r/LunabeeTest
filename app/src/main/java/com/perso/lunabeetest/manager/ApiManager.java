package com.perso.lunabeetest.manager;

import android.content.Context;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.perso.lunabeetest.R;
import com.perso.lunabeetest.bean.UnsplashCard;
import com.perso.lunabeetest.serializer.UnsplashCardSerializer;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnaud on 23/08/16.
 */
public class ApiManager {

    private static String API_URL = "https://api.unsplash.com/photos/?client_id=97d4344126dcf4be7a0e5e0d5db34c14f71f9965ae8037a80f19f48221481650";
    private static ApiManager ourInstance = new ApiManager();

    public static ApiManager getInstance() {
        return ourInstance;
    }

    private ApiManager() {
    }

    public interface OnPhotoReceived {
        void onReceived(List<UnsplashCard> newCards);
    }

    public void getPhotosByVolley(Context context, int currentPage, final OnPhotoReceived receiver){
        //Récupère la liste des photos sur unsplashed.com
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UnsplashCard.class, new UnsplashCardSerializer());
        gsonBuilder.setPrettyPrinting();
        final Gson gson = gsonBuilder.create();

        String apiUrl = String.format(API_URL + "&page=" +String.valueOf(currentPage));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,apiUrl,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        Type listType = new TypeToken<ArrayList<UnsplashCard>>(){ }.getType();
                        List<UnsplashCard> newCards = new ArrayList<>();
                        newCards = gson.fromJson(response.toString(),listType);
                        receiver.onReceived(newCards);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        Volley.newRequestQueue(context).add(jsonArrayRequest);
    }
}
