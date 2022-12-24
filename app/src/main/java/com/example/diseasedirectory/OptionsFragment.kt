package com.example.diseasedirectory

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.diseasedirectory.enum.FontFamilyName
import com.example.diseasedirectory.enum.FontSizeEnum
import com.example.diseasedirectory.textsizeutils.FontSize
import com.example.diseasedirectory.typefaceutils.Typeface
import kotlinx.android.synthetic.main.fragment_options.*

class OptionsFragment : Fragment() {

    lateinit var editorFont: SharedPreferences.Editor
    lateinit var preferences: SharedPreferences

    companion object{
        private const val SHARED_PREF_NAME = "Name"
        private const val KEY_FONT_FAMILY = "FontFamily"
        private const val KEY_FONT_SIZE = "FontSize"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
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

        preferences = requireContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        editorFont = preferences.edit()

        changeFontFamily()
        changeFonSize()
    }

    private fun changeFonSize() {
        try {
            val fontSizeInt = preferences.getInt(KEY_FONT_SIZE, 1)
            textFontSize.text = FontSizeEnum.getFontSizeName(fontSizeInt)
            seekBarFontSize.progress = fontSizeInt

            seekBarFontSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    textFontSize.text = FontSizeEnum.getFontSizeName(progress)

                    FontSize.setFontSizeTextView(progress, textFontSize)

                    editorFont.putInt(KEY_FONT_SIZE,progress).apply()
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
        }catch (e: Exception){
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun changeFontFamily(){
        try {
            val fontList: List<String> = listOf("Выберите шрифт", "Sans-Sarif", "Caveat", "Lora", "Lora-Italic",
                "Pacifico", "Rubik", "Rubik-Italic", "Default")

            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, fontList)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFontFamily.adapter = arrayAdapter

            spinnerFontFamily.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if(position != 0){
                        Typeface.setFontFamilyTextView(requireContext(), position, tvFontFamily)
                        editorFont.putInt(KEY_FONT_FAMILY, position).apply()
                    }
                    val fontFamilyInt = preferences.getInt(KEY_FONT_FAMILY, 0)
                    spinnerFontFamily.setSelection(fontFamilyInt)

                    buttonSetOptions.setOnClickListener {
                        setFontFamily(fontFamilyInt)
                        setFontSize()
                        startActivity(Intent.makeRestartActivityTask(activity?.intent?.component))
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }catch (e: Exception){
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun setFontSize(fonSizeInt: Int = preferences.getInt(KEY_FONT_SIZE, 1),
                context: Context = requireContext()) =
        FontSize.setFontSizeApp(context, FontSizeEnum.getFontSize(fonSizeInt))

    private fun setFontFamily(fontFamilyInt: Int, context: Context = requireContext()) =
        Typeface.setFontFamilyApp(context, FontFamilyName.getFontFamily(fontFamilyInt))
}