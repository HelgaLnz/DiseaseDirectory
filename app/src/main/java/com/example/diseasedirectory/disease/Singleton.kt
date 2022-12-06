package com.example.diseasedirectory.disease

import android.content.Context
import com.example.diseasedirectory.jsonutils.JsonReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Singleton {
    companion object {
        private var diseases: ArrayList<Disease>? = null

        fun getDiseases(applicationContext: Context): ArrayList<Disease> {
            return if (diseases != null) diseases!!
            else {
                diseases = Gson().fromJson(JsonReader.getJsonDataFromAsset(applicationContext, "diseases.json"),
                    object : TypeToken<ArrayList<Disease>>() {}.type)

                diseases!!
            }
        }
    }
}