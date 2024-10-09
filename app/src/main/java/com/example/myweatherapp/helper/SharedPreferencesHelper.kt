package com.example.myweatherapp.helper

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SharedPreferencesHelper(private val context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("saveData", Context.MODE_PRIVATE)



    fun saveArrayList(list: ArrayList<String>) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString("CityList", json)
        editor.apply()
    }

    fun getArrayList(): ArrayList<String>? {
        val gson = Gson()
        val json = sharedPreferences.getString("CityList", null)
        val type = object : TypeToken<ArrayList<String>>() {

        }.type

        var listOfCity = gson.fromJson<ArrayList<String>>(json, type)
        if (listOfCity == null) {
            listOfCity = arrayListOf<String>()
            listOfCity.add("Ottawa")
            listOfCity.add("Toronto")
            listOfCity.add("New York")
        }
        return listOfCity
    }

    fun saveCity(city: String) {

        var listOfCity = getArrayList()
        if (listOfCity == null) {
            listOfCity = arrayListOf<String>()
        }

        if (!listOfCity.contains(city)) {
            listOfCity.add(city)
        }
        saveArrayList(listOfCity)
    }


}