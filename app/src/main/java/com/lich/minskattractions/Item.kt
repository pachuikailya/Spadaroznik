package com.lich.minskattractions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Minsk_objects")
data class Item(
    @PrimaryKey
    var id:Int?=null,

    @ColumnInfo(name="name")
    var name: String,

    @ColumnInfo(name="address")
    var address: String,

    @ColumnInfo(name="category")
    var cat: String,

    @ColumnInfo(name="description")
    var desc: String,

    @ColumnInfo(name="history")
    var hist: String,

    @ColumnInfo(name="photo")
    var image: String,

    @ColumnInfo(name="latitude")
    var lat: Double,

    @ColumnInfo(name="longitude")
    var longit: Double


)
