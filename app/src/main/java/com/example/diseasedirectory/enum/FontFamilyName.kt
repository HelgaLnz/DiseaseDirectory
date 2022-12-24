package com.example.diseasedirectory.enum

import android.graphics.Typeface
import com.example.diseasedirectory.R

enum class FontFamilyName(val position: Int, val id: Int?) {
    Sans_Serif(1, R.font.sans_serif ),
    Caveat(2, R.font.caveat),
    Lora_Italic(3, R.font.lora),
    Lora(4, R.font.lora_italic),
    Pacifico(5, R.font.pacifico),
    Rubik_Italic(6, R.font.rubik_italic),
    Rubik(7, R.font.rubik),
    sans_default(8, 0),
    NoFontFamily(9, -1);

    companion object{
        fun getFontFamily(id: Int): FontFamilyName{
            return when (id) {
                Sans_Serif.position -> Sans_Serif
                Caveat.position -> Caveat
                Lora.position -> Lora
                Lora_Italic.position -> Lora_Italic
                Pacifico.position -> Pacifico
                Rubik.position -> Rubik
                Rubik_Italic.position -> Rubik_Italic
                sans_default.position -> sans_default
                else -> NoFontFamily
            }
        }

        fun getFontFamily(fontFamilyString: String): FontFamilyName{
            return when (fontFamilyString) {
                Sans_Serif.name -> Sans_Serif
                Caveat.name -> Caveat
                Lora.name -> Lora
                Lora_Italic.name -> Lora_Italic
                Pacifico.name -> Pacifico
                Rubik.name -> Rubik
                Rubik_Italic.name -> Rubik_Italic
                sans_default.name -> sans_default
                else -> NoFontFamily
            }
        }
        fun getFontFamilyId(position: Int): Int{
            return when (position) {
                Sans_Serif.position -> Sans_Serif.id!!
                Caveat.position -> Caveat.id!!
                Lora.position -> Lora.id!!
                Lora_Italic.position -> Lora_Italic.id!!
                Pacifico.position -> Pacifico.id!!
                Rubik.position -> Rubik.id!!
                Rubik_Italic.position -> Rubik_Italic.id!!
                else -> NoFontFamily.id!!
            }
        }
    }

}