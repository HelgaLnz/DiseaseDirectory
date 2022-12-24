package com.example.diseasedirectory.textsizeutils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.diseasedirectory.R
import com.example.diseasedirectory.enum.FontSizeEnum
import com.example.diseasedirectory.typefaceutils.Typeface

class FontSize {
    companion object{
        var spTextSize : SharedPreferences? = null
        var editorTextSize: SharedPreferences.Editor? = null
        const val KEY_FONT_SIZE_STRING = "FontSize"
        private const val SHARED_PREF_NAME_FONT_SIZE = "FontSize"

        fun initialSharedPreferences(context: Context): SharedPreferences {
            spTextSize = context.getSharedPreferences(SHARED_PREF_NAME_FONT_SIZE, Context.MODE_PRIVATE)
            return spTextSize!!
        }

        fun setFontSizeApp(context: Context, fontSize: FontSizeEnum){
            try {
                editorTextSize = initialSharedPreferences(context).edit()
                editorTextSize!!.putString(KEY_FONT_SIZE_STRING, fontSize.name).apply()
                when(fontSize){
                    FontSizeEnum.Small -> context.resources.configuration.fontScale = FontSizeEnum.Small.coef
                    FontSizeEnum.Default -> context.resources.configuration.fontScale = FontSizeEnum.Default.coef
                    FontSizeEnum.Large -> context.resources.configuration.fontScale = FontSizeEnum.Large.coef
                }
                context.resources.updateConfiguration(context.resources.configuration, context.resources.displayMetrics)

            }catch (e: Exception){
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
        fun setFontSizeTextView(progress: Int, textView: TextView){
            when(progress){
                0 -> textView.textSize = 14 * FontSizeEnum.Small.coef
                1 -> textView.textSize = 14 * FontSizeEnum.Default.coef
                2 -> textView.textSize = 14 * FontSizeEnum.Large.coef
            }
        }
        fun getTextSize(textSize: String): FontSizeEnum{
            return when(textSize){
                FontSizeEnum.Small.name -> FontSizeEnum.Small
                FontSizeEnum.Default.name -> FontSizeEnum.Default
                FontSizeEnum.Large.name -> FontSizeEnum.Large
                else -> FontSizeEnum.Default
            }
        }
    }
}