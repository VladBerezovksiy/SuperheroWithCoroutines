package com.example.superherowithcoroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyRecycleViewAdapter(private val superhero: List<SuperheroResponse>?) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listItemViewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_layout, parent, false)
        return MyViewHolder(listItemViewHolder)
    }

    override fun getItemCount(): Int = superhero?.size!!

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = superhero?.get(position)?.name
        holder.work.text = superhero?.get(position)?.works?.occupation
        Glide.with(holder.itemView)
            .load(superhero?.get(position)?.images?.xs)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val mainActivity = it.context as AppCompatActivity
            val detailsFragment = DetailsFragment()
            detailsFragment.setSuperHero(superhero?.get(position))
            mainActivity.supportFragmentManager.beginTransaction()
                .add(R.id.list, detailsFragment)
                .addToBackStack("Details_Fragment")
                .commit()
        }
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.superheroName)
    val work: TextView = itemView.findViewById(R.id.superheroDesc)
    val image: ImageView = itemView.findViewById(R.id.superheroImage)
}