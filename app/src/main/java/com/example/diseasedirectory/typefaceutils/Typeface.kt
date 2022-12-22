package com.example.diseasedirectory.typefaceutils

import android.content.Context
import android.content.SharedPreferences
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.diseasedirectory.R
import com.example.diseasedirectory.enum.FontFamilyName
import java.text.FieldPosition

class Typeface {
    companion object{
        var sharedPreferences : SharedPreferences? = null
        var editor: SharedPreferences.Editor? = null
        const val KEY_FONT_FAMILY_STRING = "FontFamily"
        private const val SHARED_PREF_NAME_FONT_FAMILY = "fontFamily"

        fun initialSharedPreferences(context: Context): SharedPreferences {
            sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME_FONT_FAMILY, Context.MODE_PRIVATE)
            return sharedPreferences!!
        }

        fun setFontFamilyApp(context: Context, font: FontFamilyName) {
            try {
                editor = initialSharedPreferences(context).edit()
                editor!!.putString(KEY_FONT_FAMILY_STRING, font.name)
                editor!!.apply()
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
            when(position){
                1 -> textView.typeface = ResourcesCompat.getFont(context, R.font.sans_serif)
                2 -> textView.typeface = ResourcesCompat.getFont(context, R.font.caveat)
                3 -> textView.typeface = ResourcesCompat.getFont(context, R.font.lora)
                4 -> textView.typeface = ResourcesCompat.getFont(context, R.font.lora_italic)
                5 -> textView.typeface = ResourcesCompat.getFont(context, R.font.pacifico)
                6 -> textView.typeface = ResourcesCompat.getFont(context, R.font.rubik)
                7 -> textView.typeface = ResourcesCompat.getFont(context, R.font.rubik_italic)
                8 -> textView.typeface = android.graphics.Typeface.SANS_SERIF
            }
        }

        fun getFontFamily(fontFamilyString: String): FontFamilyName{
            return when (fontFamilyString) {
                FontFamilyName.Sans_Serif.name -> FontFamilyName.Sans_Serif
                FontFamilyName.Caveat.name -> FontFamilyName.Caveat
                FontFamilyName.Lora.name -> FontFamilyName.Lora
                FontFamilyName.Lora_Italic.name -> FontFamilyName.Lora_Italic
                FontFamilyName.Pacifico.name -> FontFamilyName.Pacifico
                FontFamilyName.Rubik.name -> FontFamilyName.Rubik
                FontFamilyName.Rubik_Italic.name -> FontFamilyName.Rubik_Italic
                FontFamilyName.sans_default.name -> FontFamilyName.sans_default
                else -> FontFamilyName.NoFontFamily
            }
        }

        fun getFontFamily(position: Int): FontFamilyName{
            return when (position) {
                1 -> FontFamilyName.Sans_Serif
                2 -> FontFamilyName.Caveat
                3 -> FontFamilyName.Lora
                4 -> FontFamilyName.Lora_Italic
                5 -> FontFamilyName.Pacifico
                6 -> FontFamilyName.Rubik
                7 -> FontFamilyName.Rubik_Italic
                8 -> FontFamilyName.sans_default
                else -> FontFamilyName.NoFontFamily
            }
        }
    }
}