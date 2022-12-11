package com.khater.retromvvm.database.localData.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.khater.retromvvm.database.localData.data.LocalWall

@Database(entities = [LocalWall::class], version = 1)
abstract class WallDatabase : RoomDatabase() {

            abstract fun wallDao(): WallDao

            companion object {
                private const val DATABASE_NAME = "MyWallDataBase"

        @Volatile
        private var instance: WallDatabase? = null

        fun getInstance(context: Context): WallDatabase {
            return instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }
        }
        fun getInstanceWithoutContext():WallDatabase{
            return instance!!
        }


        private fun buildDatabase(context: Context): WallDatabase {
            return Room.databaseBuilder(context, WallDatabase::class.java, DATABASE_NAME).build()
        }
    }
}