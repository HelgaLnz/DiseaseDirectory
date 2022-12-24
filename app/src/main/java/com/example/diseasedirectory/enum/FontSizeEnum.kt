package com.example.diseasedirectory.enum

import android.content.Context
import com.example.diseasedirectory.OptionsFragment
import com.example.diseasedirectory.textsizeutils.FontSize

enum class FontSizeEnum(val coef: Float, val value : Int) {
    Small((0.9).toFloat(), 0),
    Default((1).toFloat(), 1),
    Large((1.3).toFloat(), 2);
    companion object{
        fun getFontSizeName(fontSizeValue: Int): String{
            return when(fontSizeValue){
                0 -> Small.name
                1 -> Default.name
                2 -> Large.name
                else -> Default.name
            }
        }
        fun getFontSize(value: Int): FontSizeEnum{
            return when(value){
                Small.value -> Small
                Default.value -> Default
                Large.value -> Large
                else -> Default
            }
        }

    }
}