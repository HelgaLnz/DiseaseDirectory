package com.example.diseasedirectory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.diseasedirectory.adapter.DiseaseAdapter
import com.example.diseasedirectory.disease.Disease
import com.example.diseasedirectory.disease.Singleton
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_disease_list.*
import kotlinx.android.synthetic.main.fragment_disease_list.view.*

class DiseaseListFragment : Fragment() {
    companion object {
        private const val CATEGORY_KEY = "Category"

        fun newInstance(category: String): DiseaseListFragment {
            val fragment = DiseaseListFragment()
            val args = Bundle()
            args.putString(CATEGORY_KEY, category)
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var diseases: ArrayList<Disease>
    private lateinit var adapter: DiseaseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_disease_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = arguments?.getString(CATEGORY_KEY)

        diseases =
            if(category.isNullOrEmpty()) Singleton.getDiseases(requireActivity().applicationContext)
            else ArrayList(Singleton.getDiseases(requireActivity().applicationContext).filter {
                it.category == category })

        adapter = DiseaseAdapter(diseases, this)

        diseaseList.adapter = adapter
    }
}