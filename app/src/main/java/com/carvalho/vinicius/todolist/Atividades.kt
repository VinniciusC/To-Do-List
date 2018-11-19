package com.carvalho.vinicius.todolist

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class Atividades(var nome: String,
                  @PrimaryKey (autoGenerate = true)
                  var id: Int = 0):Serializable{
}