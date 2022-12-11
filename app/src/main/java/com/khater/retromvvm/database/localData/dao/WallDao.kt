package com.khater.retromvvm.database.localData.dao

import androidx.room.*
 import com.khater.retromvvm.database.localData.data.LocalWall
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface WallDao {

    @Insert
    fun insert(wall: LocalWall):Completable

    @Delete
    fun delete(wall: LocalWall):Completable

    @Update
    fun update(wall: LocalWall):Completable

    @Query("SELECT * FROM note_table")
    fun getAllNote(): Observable<List<LocalWall>>
}