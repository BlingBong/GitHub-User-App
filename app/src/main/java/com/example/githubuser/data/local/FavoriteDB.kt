package com.example.githubuser.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubuser.model.UsersItem

@Database(
    entities = [UsersItem::class],
    version = 1
)
abstract class FavoriteDB: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDB? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteDB {
            if (INSTANCE == null) {
                synchronized(FavoriteDB::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteDB::class.java, "favorite_db")
                        .build()
                }
            }
            return INSTANCE as FavoriteDB
        }
    }
}