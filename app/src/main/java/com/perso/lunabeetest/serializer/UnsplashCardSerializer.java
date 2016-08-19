package com.perso.lunabeetest.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.perso.lunabeetest.bean.UnsplashCard;

import java.lang.reflect.Type;

/**
 * Created by arnaud on 16/08/16.
 */
public class UnsplashCardSerializer implements JsonSerializer<UnsplashCard>, JsonDeserializer<UnsplashCard> {
    @Override
    public UnsplashCard deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        UnsplashCard card = new UnsplashCard();
        JsonObject jsonUnsplashCard = json.getAsJsonObject();

        card.setAuthor(jsonUnsplashCard.get("user").getAsJsonObject().get("name").getAsString());
        card.setImageUrl(jsonUnsplashCard.get("urls").getAsJsonObject().get("regular").getAsString());
        return card;
    }

    @Override
    public JsonElement serialize(UnsplashCard src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
