package com.example.diseasedirectory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diseasedirectory.adapter.CategoryAdapter
import com.example.diseasedirectory.disease.Disease
import com.example.diseasedirectory.disease.Singleton
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = ArrayList(
            Singleton.getDiseases(requireActivity().applicationContext)
                .map { t -> t.category}
                .distinct())

        val adapter = CategoryAdapter(categories, this)

        diseaseCategoriesList.adapter = adapter
    }
}