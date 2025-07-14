package com.example.movieapi.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapi.data.room.dao.MovieDao
import com.example.movieapi.data.room.entity.MovieEntity

@Database(entities = [MovieEntity ::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}