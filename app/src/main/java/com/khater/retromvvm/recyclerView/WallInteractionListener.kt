package com.khater.retromvvm.recyclerView

import android.view.View
import com.khater.retromvvm.model.domain.Data

interface WallInteractionListener {
    fun onClickItem(data : Data, view: View)
}