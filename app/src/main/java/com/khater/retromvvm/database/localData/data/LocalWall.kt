package com.khater.retromvvm.database.localData.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "NOTE_TABLE")
data class LocalWall(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val wallUrl: String?
)
