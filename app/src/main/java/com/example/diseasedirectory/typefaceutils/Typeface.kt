package com.example.diseasedirectory.typefaceutils

import android.content.Context
import android.content.SharedPreferences
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.diseasedirectory.R
import com.example.diseasedirectory.enum.FontFamilyName

class Typeface {
    companion object{
        var spFontFamily : SharedPreferences? = null
        var editorFontFamily: SharedPreferences.Editor? = null
        const val KEY_FONT_FAMILY_STRING = "FontFamily"
        private const val SHARED_PREF_NAME_FONT_FAMILY = "fontFamily"

        fun initialSharedPreferences(context: Context): SharedPreferences {
            spFontFamily = context.getSharedPreferences(SHARED_PREF_NAME_FONT_FAMILY, Context.MODE_PRIVATE)
            return spFontFamily!!
        }

        fun setFontFamilyApp(context: Context, font: FontFamilyName) {
            try {
                editorFontFamily = initialSharedPreferences(context).edit()
                editorFontFamily!!.putString(KEY_FONT_FAMILY_STRING, font.name).apply()
                when(font){
                    FontFamilyName.Sans_Serif -> context.setTheme(R.style.Theme_DiseaseDirectory_SansSerif)
                    FontFamilyName.Caveat -> context.setTheme(R.style.Theme_DiseaseDirectory_Caveat)
                    FontFamilyName.Lora -> context.setTheme(R.style.Theme_DiseaseDirectory_Lora)
                    FontFamilyName.Lora_Italic -> context.setTheme(R.style.Theme_DiseaseDirectory_LoraItalic)
                    FontFamilyName.Pacifico -> context.setTheme(R.style.Theme_DiseaseDirectory_Pacifico)
                    FontFamilyName.Rubik -> context.setTheme(R.style.Theme_DiseaseDirectory_Rubik)
                    FontFamilyName.Rubik_Italic -> context.setTheme(R.style.Theme_DiseaseDirectory_RubikItalic)
                    FontFamilyName.sans_default -> context.setTheme(R.style.Theme_DiseaseDirectory_SansDefault)
                    else -> context.setTheme(R.style.Theme_DiseaseDirectory_SansDefault)
                }
            }catch (e: Exception){
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }

        fun setFontFamilyTextView(context: Context, position: Int, textView: TextView){
            if(position == 8)
                textView.typeface = android.graphics.Typeface.SANS_SERIF
            else
                textView.typeface = ResourcesCompat.getFont(context, FontFamilyName.getFontFamilyId(position))
        }
    }
}