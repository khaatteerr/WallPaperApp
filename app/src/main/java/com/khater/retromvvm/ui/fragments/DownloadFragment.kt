package com.khater.retromvvm.ui.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.bumptech.glide.Glide
import com.khater.retromvvm.R
import com.khater.retromvvm.databinding.FragmentDownloadBinding
import com.khater.retromvvm.utils.BlurHashDecoder
import com.khater.retromvvm.utils.Constants


class DownloadFragment : Fragment() {

    private lateinit var binding : FragmentDownloadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding = FragmentDownloadBinding.inflate(inflater, container, false)
        val url =  arguments?.getString("urlImage")
        loadWall(url.toString())
        bottomSheet(url.toString())
        addCallBacks()
        return binding.root
    }

    private fun loadWall(url: String) {


        val blurHash = arguments?.getString("blurHashString")


        val blurHashAsDrawable = BlurHashDecoder.decode(blurHash)

        Glide.with(requireActivity())
            .asBitmap()
            .load(url)
            .centerCrop()
            .placeholder(blurHashAsDrawable?.toDrawable(this.resources))
            .error(blurHashAsDrawable)
            .into(binding.downloadImageView)

        binding.constraintDownload.background = BitmapDrawable(this.resources, blurHashAsDrawable)

    }

    private fun addCallBacks() {
        binding.backButton.setOnClickListener {v->
           findNavController(v).popBackStack()

        }

    }

    private fun bottomSheet(url:String) {

        val bottomSheetFragment = BottomSheetFragment(url )
        binding.downloadButton.setOnClickListener {
            bottomSheetFragment.show(requireActivity().supportFragmentManager, "bottomSheetDialog")
        }

    }
}