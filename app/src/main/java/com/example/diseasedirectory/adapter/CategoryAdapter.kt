package com.example.diseasedirectory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.diseasedirectory.DiseaseFragment
import com.example.diseasedirectory.DiseaseListFragment
import com.example.diseasedirectory.R
import kotlinx.android.synthetic.main.disease_item.view.*

class CategoryAdapter(dataSet: ArrayList<String>, context: Fragment) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>()  {

        private val categoriesArrayList: ArrayList<String>
        private val fragment: Fragment
        init {
            categoriesArrayList = dataSet
            fragment = context
            notifyDataSetChanged()
        }

        class ViewHolder(view: View): RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.itemView.category.text = categoriesArrayList[position]

            holder.itemView.setOnClickListener {
                fragment.activity?.title = fragment.activity?.getString(R.string.app_name)

                val category = categoriesArrayList[position]

                fragment.parentFragmentManager.beginTransaction()
                    .replace(R.id.navHostFragment, DiseaseListFragment.newInstance(category))
                    .addToBackStack(DiseaseListFragment::class.java.name)
                    .commit()
            }

            holder.itemView.cardView.startAnimation(
                AnimationUtils.loadAnimation(holder.itemView.context,
                R.anim.fade_in))
        }

        override fun getItemCount(): Int {
            return categoriesArrayList.size
        }
}