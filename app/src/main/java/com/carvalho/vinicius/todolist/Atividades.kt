package com.carvalho.vinicius.todolist

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class Atividades (val nome: String,
                  @PrimaryKey (autoGenerate = true)
                  val id: Int = 0):Serializable{
}