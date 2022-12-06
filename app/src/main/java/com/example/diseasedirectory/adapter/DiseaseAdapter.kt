package com.example.diseasedirectory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.diseasedirectory.DiseaseFragment
import com.example.diseasedirectory.R
import com.example.diseasedirectory.disease.Disease
import kotlinx.android.synthetic.main.disease_item.view.*

class DiseaseAdapter(dataSet: ArrayList<Disease>, context: Fragment) :
    RecyclerView.Adapter<DiseaseAdapter.ViewHolder>() {

    private val diseaseArrayList: ArrayList<Disease>
    private val fragment: Fragment
    init {
        diseaseArrayList = dataSet
        fragment = context
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.disease_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.title.text = diseaseArrayList[position].title
        holder.itemView.category.text = diseaseArrayList[position].category

        holder.itemView.setOnClickListener {
            val disease = diseaseArrayList[position]

            fragment.parentFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, DiseaseFragment.newInstance(disease))
                .addToBackStack(DiseaseFragment::class.java.name)
                .commit()
        }

        holder.itemView.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context,
            R.anim.fade_in))
    }

    override fun getItemCount(): Int {
        return diseaseArrayList.size
    }

    fun getItems() = diseaseArrayList
}