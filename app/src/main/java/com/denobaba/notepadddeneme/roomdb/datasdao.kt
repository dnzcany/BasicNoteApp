package com.denobaba.notepadddeneme.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.denobaba.notepadddeneme.model.datas
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable


@Dao
interface datasdao {

    @Query("SELECT * FROM datas ")
    fun getAll(): Flowable<List<datas>>

    @Insert
    fun insert(place:datas) :Completable

    @Delete
    fun delete(place:datas) : Completable





}


