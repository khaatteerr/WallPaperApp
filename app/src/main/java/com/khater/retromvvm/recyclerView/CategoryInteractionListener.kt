package com.khater.retromvvm.recyclerView

import android.view.View
import com.khater.retromvvm.model.domain.Category

interface CategoryInteractionListener {
    fun onClickCategory(category :Category, view: View)
}