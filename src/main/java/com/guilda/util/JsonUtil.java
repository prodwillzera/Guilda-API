/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilda.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author nmare
 */
public class JsonUtil {
    private static final Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}
