package com.khater.retromvvm.utils

object Constants {
    const val HOME = "Home"
    const val POPULAR = "Popular"
    const val RANDOM = "Random"
    const val CATEGORY =  "Category"
    const val CATEGORY_NAME = "Categories"

    const val DOWNLOAD_WALL = "downloadWall"
    const val IMAGE_NAME= "imageName"

    object BackGroundState{
        const val lockScreen = 2
        const val backGround = 1
    }

    object NavigationIntent{
        const val FromMainToDownload = 1
        const val FromSearchToDownload = 2
        const val FromCategoryToDownload = 3
    }
 }