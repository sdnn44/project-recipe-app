package com.example.recipescook.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipescook.Entity.Recipe
import com.example.recipescook.R

class CustomAdapter(private var recipeList: List<Recipe>, context: Context, private val onItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<CustomAdapter.RecipesViewHolder>() {

    var images = intArrayOf(
        R.drawable.image,
        R.drawable.image3,
        R.drawable.image2,
        R.drawable.image_item1
    )

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecipesViewHolder {
//        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_recipe_layout, viewGroup, false)
//        return ViewHolder(v)
        val context = viewGroup.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.card_recipe_layout, viewGroup, false)
        return RecipesViewHolder(view, onItemClicked)

    }

    override fun onBindViewHolder(viewHolder: RecipesViewHolder, i: Int) {
        viewHolder.itemTitle.text = recipeList[i].recipeName
        viewHolder.itemDetails.text = recipeList[i].recipeDescription;
        viewHolder.itemImage.setImageResource(images[i%4]);
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    inner class RecipesViewHolder(
        itemView: View,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        // to be determined!
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetails: TextView

        init {
            itemView.setOnClickListener(this)
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetails = itemView.findViewById(R.id.item_detail)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition // absoluteAdapterPosition
            onItemClicked(position)
        }
    }


}