package com.khater.retromvvm.repository

import com.khater.retromvvm.database.localData.dao.WallDatabase
import com.khater.retromvvm.database.localData.data.LocalWall
import io.reactivex.rxjava3.core.Completable

class WallRepositery {
     val dao = WallDatabase.getInstanceWithoutContext().wallDao()
     fun insertNewNote(note: LocalWall): Completable {
        return dao.insert(note)
    }

    fun getAllNotes() = dao.getAllNote()
}