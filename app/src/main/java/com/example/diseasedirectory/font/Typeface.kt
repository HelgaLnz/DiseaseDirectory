package com.example.diseasedirectory.font

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.example.diseasedirectory.MainActivity
import com.example.diseasedirectory.R
import com.example.diseasedirectory.enum.FontName

class Font {
    companion object{
        fun getFont(context: Context, fontName: FontName): Typeface? {
            return when(fontName){
                FontName.Caveat -> ResourcesCompat.getFont(context, R.font.caveat)
                FontName.Lora_Italic -> ResourcesCompat.getFont(context, R.font.lora_italic)
                FontName.Lora -> ResourcesCompat.getFont(context, R.font.lora)
                FontName.Pacifico -> ResourcesCompat.getFont(context, R.font.pacifico)
                FontName.Rubik_Italic -> ResourcesCompat.getFont(context, R.font.rubik_italic)
                FontName.Rubik -> ResourcesCompat.getFont(context, R.font.rubik)
                else -> null
            }
        }

        fun setFontApp(context: Context,font: FontName){
            when(font){
                FontName.Sans_Serif -> context.setTheme(R.style.Theme_DiseaseDirectory_SansSerif)
                FontName.Caveat -> context.setTheme(R.style.Theme_DiseaseDirectory_Caveat)
                FontName.Lora -> context.setTheme(R.style.Theme_DiseaseDirectory_Lora)
                FontName.Lora_Italic -> context.setTheme(R.style.Theme_DiseaseDirectory_LoraItalic)
                FontName.Pacifico -> context.setTheme(R.style.Theme_DiseaseDirectory_Pacifico)
                FontName.Rubik -> context.setTheme(R.style.Theme_DiseaseDirectory_Rubik)
                FontName.Rubik_Italic -> context.setTheme(R.style.Theme_DiseaseDirectory_RubikItalic)
            }
        }
    }
}