package com.khater.retromvvm.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khater.retromvvm.R
import com.khater.retromvvm.databinding.CategoryRowBinding
import com.khater.retromvvm.model.domain.Category
import com.khater.retromvvm.ui.fragments.MainFragmentDirections

class CategoriesAdapter(private val category: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {


    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CategoryRowBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = category[position]
        holder.binding.apply {
            categoryTextView.text = currentCategory.categoryName

            Glide.with(holder.itemView.context)
                .load(currentCategory.imageUrl)
                .centerCrop()
                .error(R.color.babyBlue)
                .into(categoryImageView)
        }
        holder.itemView.setOnClickListener { v ->

            val action = MainFragmentDirections.actionTestFragmentToSpecificCategoryFragment(currentCategory.categoryName)
            Navigation.findNavController(v)
                .navigate(action)

        }
    }


    override fun getItemCount() = category.size
}