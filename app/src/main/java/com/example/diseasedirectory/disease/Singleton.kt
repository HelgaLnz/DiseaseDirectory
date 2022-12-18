package com.example.diseasedirectory.disease

import android.content.Context
import com.example.diseasedirectory.dbutils.DbHelper
import com.example.diseasedirectory.jsonutils.JsonReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Singleton {
    companion object {
        private var diseases: ArrayList<Disease>? = null

        fun getDiseases(applicationContext: Context, fileName: String): ArrayList<Disease> {
            return if (diseases != null) diseases!!
            else {
                diseases = Gson().fromJson(JsonReader.getJsonDataFromAsset(applicationContext, fileName),
                    object : TypeToken<ArrayList<Disease>>() {}.type)

                diseases!!
            }
        }

        fun getDiseases(applicationContext: Context): ArrayList<Disease> {
            return if (diseases != null) diseases!!
            else {
                diseases = ArrayList()
                val database = DbHelper(applicationContext).database
                val cursor = database.rawQuery("SELECT * FROM Disease", null)
                while(cursor.moveToNext()) {
                    val title = cursor.getString(1)
                    val info = cursor.getString(2)
                    val type: String? = cursor.getString(3)

                    var imagesMutable = mutableListOf("", "")
                    val imagesImmutable: List<String> = cursor.getString(4).split(",")
                    if(imagesImmutable.size == 2) {
                        imagesMutable[0] = imagesImmutable[0]
                        imagesMutable[1] = imagesImmutable[1]
                    } else {
                        imagesMutable[0] = imagesImmutable[0]
                        imagesMutable[1] = ""
                    }

                    val reasons: String? = cursor.getString(5)
                    val classifications: String? = cursor.getString(6)
                    val symptoms: String? = cursor.getString(7)
                    val diagnostics: String? = cursor.getString(8)
                    val category = cursor.getString(9)

                    val disease = Disease(title, info, type ?: "", imagesMutable.toList(),
                        reasons ?: "", classifications ?: "", symptoms ?: "",
                        diagnostics ?: "", category)

                    diseases?.add(disease)
                }

                return diseases!!
            }
        }
    }
}