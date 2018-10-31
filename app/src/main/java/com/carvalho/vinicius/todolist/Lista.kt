package com.carvalho.vinicius.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_lista.*

class Lista : AppCompatActivity() {

    val itens = ArrayList<Atividades>()
    private val ADICIONA_ITEM = 1
    private val ITEM = "Item"
    var index: Int = -1
    val adapter = ItemAdapter(itens)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)


        Novobutton.setOnClickListener {
            val novatarefa = Intent(this,NovoItem::class.java)
            startActivityForResult(novatarefa,1)
        }

        adapter.configuraCliqueFeito { index ->
            itens.removeAt(index)
            carregaLista()
        }

    }

    override fun onResume() {
        super.onResume()
        carregaLista()
    }

    fun carregaLista() {
        val layoutManager = LinearLayoutManager(this)

        adapter.configuraClique {
            item, index ->
            this.index = index
            val editarItem = Intent(this, NovoItem::class.java)
            editarItem.putExtra(NovoItem.ITEM, itens.get(index))
            startActivityForResult(editarItem,ADICIONA_ITEM)
        }

        rvLista.adapter = adapter
        rvLista.layoutManager = layoutManager

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            val adicionarItem: Atividades? = data?.getSerializableExtra("NovoItem") as Atividades?
            if (adicionarItem != null) {
                if (index>=0){
                    itens.set(index,adicionarItem)
                    index = -1

                }else{
                itens.add(adicionarItem)
            }
            }
        }
    }

}
