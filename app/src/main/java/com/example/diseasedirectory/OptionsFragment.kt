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
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.diseasedirectory.enum.FontFamilyName
import com.example.diseasedirectory.typefaceutils.Typeface
import kotlinx.android.synthetic.main.fragment_options.*

class OptionsFragment : Fragment() {

    lateinit var editorFontFamily: SharedPreferences.Editor
    lateinit var preferences: SharedPreferences

    companion object{
        private const val SHARED_PREF_NAME = "name"
        private const val KEY_FONT_FAMILY = "FontFamily"
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
        editorFontFamily = preferences.edit()

        changeFontFamily()
        changeFonSize()
    }

    private fun changeFonSize() {
        seekBarFontSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textFontSize.text = resources.getText(R.string.font_size_number).toString().plus(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private fun changeFontFamily(){
        val fontList: List<String> = listOf("Выберите шрифт", "Sans-Sarif", "Caveat", "Lora", "Lora-Italic",
            "Pacifico", "Rubik", "Rubik-Italic", "Default")

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, fontList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFontFamily.adapter = arrayAdapter

        spinnerFontFamily.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position != 0){

                    Typeface.setFontFamilyTextView(requireContext(), position, tvFontFamily)
                    editorFontFamily.putInt(KEY_FONT_FAMILY, position)
                    editorFontFamily.apply()

                }
                    val fontFamilyInt = preferences.getInt(KEY_FONT_FAMILY, 0)
                    spinnerFontFamily.setSelection(fontFamilyInt)

                buttonSetOptions.setOnClickListener {
                    setFontFamily(fontFamilyInt, requireContext())
                    startActivity(Intent.makeRestartActivityTask(activity?.intent?.component));
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun setFontFamily(fontFamilyInt: Int, context: Context){
        when(fontFamilyInt){
            1 -> Typeface.setFontFamilyApp(context, Typeface.getFontFamily(1))
            2 -> Typeface.setFontFamilyApp(context, Typeface.getFontFamily(2))
            3 -> Typeface.setFontFamilyApp(context, Typeface.getFontFamily(3))
            4 -> Typeface.setFontFamilyApp(context, Typeface.getFontFamily(4))
            5 -> Typeface.setFontFamilyApp(context, Typeface.getFontFamily(5))
            6 -> Typeface.setFontFamilyApp(context, Typeface.getFontFamily(6))
            7 -> Typeface.setFontFamilyApp(context, Typeface.getFontFamily(7))
            8 -> Typeface.setFontFamilyApp(context, Typeface.getFontFamily(8))
        }
    }
}