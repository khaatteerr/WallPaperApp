package com.khater.retromvvm.recyclerView


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.khater.retromvvm.R
import com.khater.retromvvm.databinding.ItemRecyclerViewBinding
import com.khater.retromvvm.model.domain.Data
import com.khater.retromvvm.ui.fragments.MainFragmentDirections
import com.khater.retromvvm.ui.fragments.SearchFragmentDirections
import com.khater.retromvvm.ui.fragments.SpecificCategoryFragmentDirections
import com.khater.retromvvm.utils.BlurHashDecoder
import com.khater.retromvvm.utils.Constants


class RecyclerViewAdapter(private val navigationId: Int?) :
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

            val blurHashAsDrawable = BlurHashDecoder.blurHashBitmap(itemView.resources, data)
            Glide.with(itemView.context)
                .asBitmap()
                .load(data.smallImageUrl)
                .placeholder(blurHashAsDrawable)
                .centerCrop()
                .transition(BitmapTransitionOptions.withCrossFade(50))
                .error(blurHashAsDrawable)
                .into(binding.imageView)

            val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())

            itemView.setOnClickListener { v ->

                when (navigationId) {

                    Constants.NavigationIntent.FromSearchToDownload ->
                        Navigation.findNavController(v)
                            .navigate(
                                SearchFragmentDirections.actionSearchFragmentToDownloadFragment(
                                    imageData
                                )
                            )

                    Constants.NavigationIntent.FromMainToDownload ->
                        Navigation.findNavController(v)
                            .navigate(
                                MainFragmentDirections.actionTestFragmentToDownloadFragment(
                                    imageData
                                )
                            )

                    Constants.NavigationIntent.FromCategoryToDownload ->

                        Navigation.findNavController(v)
                            .navigate(
                                SpecificCategoryFragmentDirections.actionSpecificCategoryFragmentToDownloadFragment(
                                    imageData
                                )
                            )

                }

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


