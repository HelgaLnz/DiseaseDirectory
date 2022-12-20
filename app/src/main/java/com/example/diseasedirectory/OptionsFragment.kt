package com.example.diseasedirectory

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.text.style.TypefaceSpan
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.TypefaceCompat
import androidx.core.graphics.TypefaceCompatApi26Impl
import androidx.core.graphics.TypefaceCompatUtil
import androidx.fragment.app.Fragment
import com.example.diseasedirectory.enum.FontName
import kotlinx.android.synthetic.main.fragment_options.*

class OptionsFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    companion object{
        private const val SHARED_PREF_NAME = "name"
        private const val KEY_FONT_FAMILY = "FontFamily"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.item_settings)

        switchDarkTheme.isChecked = context?.resources?.configuration?.uiMode
            ?.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

        switchDarkTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        changeFontFamily()
    }

    private fun changeFontFamily(){
        val fontList: List<String> = listOf("Выберите шрифт", "Sans-Sarif", "Caveat", "Lora", "Lora-Italic",
            "Pacifico", "Rubik", "Rubik-Italic")

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, fontList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFontFamily.adapter = arrayAdapter

        spinnerFontFamily.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position != 0){
                    editor.putInt(KEY_FONT_FAMILY, position)
                    editor.apply()
                }
                var fontFamilyInt = sharedPreferences.getInt(KEY_FONT_FAMILY, 0)
                spinnerFontFamily.setSelection(fontFamilyInt)

                when(fontFamilyInt){
                    1 -> com.example.diseasedirectory.font.Font.setFontApp(requireContext(), FontName.Sans_Serif)
                    2 -> com.example.diseasedirectory.font.Font.setFontApp(requireContext(), FontName.Caveat)
                    3 -> com.example.diseasedirectory.font.Font.setFontApp(requireContext(), FontName.Lora)
                    4 -> com.example.diseasedirectory.font.Font.setFontApp(requireContext(), FontName.Lora_Italic)
                    5 -> com.example.diseasedirectory.font.Font.setFontApp(requireContext(), FontName.Pacifico)
                    6 -> com.example.diseasedirectory.font.Font.setFontApp(requireContext(), FontName.Rubik)
                    7 -> com.example.diseasedirectory.font.Font.setFontApp(requireContext(), FontName.Rubik_Italic)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}