package com.example.superherowithcoroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {

    private var oldSuperhero: SuperheroResponse? = null

    private lateinit var detailsTitle: TextView
    private lateinit var detailsDesc: TextView
    private lateinit var detailsImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fargment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsTitle = view.findViewById(R.id.detailsTitle)
        detailsDesc = view.findViewById(R.id.detailsDesc)
        detailsImage = view.findViewById(R.id.detailsImage)

        detailsTitle.text = oldSuperhero?.name
        detailsDesc.text = oldSuperhero?.works?.occupation
        Glide.with(view)
            .load(oldSuperhero?.images?.xs)
            .into(detailsImage)
    }

    fun setSuperHero(newSuperhero: SuperheroResponse?) {
        oldSuperhero = newSuperhero
    }

}