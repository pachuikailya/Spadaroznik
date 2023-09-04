package com.lich.minskattractions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item:Item)

    @Query("select * from Minsk_objects ")
    fun getAllItem(): Flow<List<Item>>

    @Query("select * from Minsk_objects where id = :itemId")
    fun getItById( itemId:Int):Item

    @Query("select * from Minsk_objects where name like :searchQuery")
    fun searchDB (searchQuery:String):Flow<List<Item>>

    @Query("select * from Minsk_objects where category = :objectType")
    fun filterDB (objectType:String):Flow<List<Item>>
}