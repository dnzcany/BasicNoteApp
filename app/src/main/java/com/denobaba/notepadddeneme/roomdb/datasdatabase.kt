package com.denobaba.notepadddeneme.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.denobaba.notepadddeneme.model.datas


@Database(entities = [datas::class], version = 1)
    abstract class datasdatabase : RoomDatabase() {
abstract fun placeDao():datasdao}
