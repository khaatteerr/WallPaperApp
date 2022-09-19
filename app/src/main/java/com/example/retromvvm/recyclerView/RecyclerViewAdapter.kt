package com.example.retromvvm.recyclerView

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.retromvvm.R
import com.example.retromvvm.databinding.ItemRecyclerViewBinding
import com.example.retromvvm.model.domain.Data
import com.example.retromvvm.ui.activities.DownloadActivity
import com.example.retromvvm.utils.Constants


class RecyclerViewAdapter :
    PagingDataAdapter<Data, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {
    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {


        holder.bind(getItem(position)!!)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return MyViewHolder(inflater)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRecyclerViewBinding.bind(view)


        fun bind(data: Data) {

            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()


            Glide.with(itemView.context)
                .load(data.smallImageUrl)
                .centerCrop()
                .error(R.color.babyBlue)
                .placeholder(circularProgressDrawable)
                .into(binding.imageView)





            itemView.setOnClickListener {
                val intent = Intent(it.context, DownloadActivity::class.java)
                intent.putExtra(Constants.DOWNLOADWALL, data.fullImageUrl)
                intent.putExtra(Constants.IMAGE_NAME, data.description)
                it.context.startActivity(intent)
            }
        }

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.blurHash == newItem.blurHash

        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem

        }
    }
}