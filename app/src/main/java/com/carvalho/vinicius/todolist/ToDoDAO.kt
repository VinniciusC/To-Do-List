package com.carvalho.vinicius.todolist

import android.arch.persistence.room.*

@Dao
interface ToDoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Atividades)

    @Query(value = "SELECT * FROM atividades")
    fun getAll(): List<Atividades>

    @Delete
    fun delete(item: Atividades)

}