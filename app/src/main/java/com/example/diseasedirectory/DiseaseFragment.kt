package com.example.diseasedirectory

import android.os.Bundle
import android.provider.MediaStore.Images
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import animationutils.AnimationPlayer
import com.davemorrissey.labs.subscaleview.ImageSource
import com.example.diseasedirectory.disease.Disease
import kotlinx.android.synthetic.main.disease_item.*
import kotlinx.android.synthetic.main.fragment_disease.*


class DiseaseFragment : Fragment() {
    companion object {
        const val DISEASE_KEY = "Disease"

        fun newInstance(disease: Disease): DiseaseFragment {
            val fragment = DiseaseFragment()
            val args = Bundle()
            args.putParcelable(DISEASE_KEY, disease)
            fragment.arguments = args

            return fragment
        }
    }
    private lateinit var disease : Disease

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_disease, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        disease = arguments?.getParcelable(DISEASE_KEY)!!

        activity?.title = disease.title
        tvInfo.text = disease.info
        tvCategory.text = disease.category

        viewVisibility()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun viewVisibility() {
        visibilityImage1()
        visibilityImage2()
        visibilityReasons()
        visibilityClassifications()
        visibilityType()
        visibilitySymptoms()
        visibilityDiagnostics()
    }

    private fun visibilityImage1() {
        val img1 = "drawable/" + disease.images[0]
        val imageResource1: Int = resources.getIdentifier(img1, "drawable", activity?.packageName)

        if(imageResource1 == 0)
            ivDisease1.visibility = View.GONE
        else
        {
            ivDisease1.setImage(ImageSource.resource(imageResource1))
            AnimationPlayer.appear(ivDisease1, 1000)
        }

    }

    private fun visibilityImage2() {
        val img2 = "drawable/" + disease.images[1]
        val imageResource2: Int = resources.getIdentifier(img2, "drawable", activity?.packageName)

        if(imageResource2 == 0)
            ivDisease2.visibility = View.GONE
        else
        {
            ivDisease2.setImage(ImageSource.resource(imageResource2))
            AnimationPlayer.appear(ivDisease2, 1000)
        }

    }

    private fun visibilityReasons() {
        if(disease.reasons.isEmpty()) {
            tvReasonsTitle.visibility = View.GONE
            tvReasons.visibility = View.GONE
        } else tvReasons.text = disease.reasons
    }

    private fun visibilityClassifications() {
        if(disease.classifications.isEmpty()) {
            tvClassificationsTitle.visibility = View.GONE
            tvClassifications.visibility = View.GONE
        } else tvClassifications.text = disease.classifications
    }

    private fun visibilityType() {
        if(disease.type.isEmpty()){
            tvTypesTitle.visibility = View.GONE
            tvTypes.visibility = View.GONE
        } else tvTypes.text = disease.type
    }

    private fun visibilitySymptoms() {
        if(disease.symptoms.isEmpty()){
            tvSymptomsTitle.visibility = View.GONE
            tvSymptoms.visibility = View.GONE
        } else tvSymptoms.text = disease.symptoms
    }

    private fun visibilityDiagnostics() {
        if(disease.diagnostics.isEmpty()) {
            tvDiagnosticsTitle.visibility = View.GONE
            tvDiagnostics.visibility = View.GONE
        } else tvDiagnostics.text = disease.diagnostics
    }
}