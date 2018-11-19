package com.carvalho.vinicius.todolist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_lista.*
import org.jetbrains.anko.activityUiThreadWithContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class Lista : AppCompatActivity() {

    var itens = ArrayList<Atividades>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)


        Novobutton.setOnClickListener {
            val novatarefa = Intent(this,NovoItem::class.java)
            startActivity(novatarefa)
        }

    }

    override fun onResume() {
        super.onResume()
        carregaLista()
    }

    fun carregaLista() {

        val todoDAO = AppDatabase.getInstance(this).ToDoDAO()
        doAsync {
            itens = todoDAO.getAll() as ArrayList<Atividades>

            activityUiThreadWithContext {
                val layoutManager = LinearLayoutManager(this)
                val adapter = ItemAdapter(itens)

                adapter.configuraClique { index ->
                    val editarItem = Intent(this, NovoItem::class.java)
                    editarItem.putExtra(NovoItem.ITEM, itens.get(index))
                    startActivity(editarItem)
                }

                adapter.configuraCliqueFeito { index ->
                    doAsync {
                        todoDAO.delete(itens.get(index))

                        uiThread {
                            carregaLista()
                        }
                    }
                }

                rvLista.adapter = adapter
                rvLista.layoutManager = layoutManager


            }
        }

    }
}
