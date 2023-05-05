package com.denobaba.notepadddeneme.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class datas(

    @ColumnInfo(name = "name")
    var name:String,

    @ColumnInfo(name = "alltext")
    var alltext: String,


    ): java.io.Serializable {

    @PrimaryKey(autoGenerate = true)
    var id =0



}

