package com.carvalho.vinicius.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_novo_item.*

class NovoItem : AppCompatActivity() {

    companion object {
        const val NovoItem: String = "NovoItem"
        const val ITEM = "Item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_item)

        val item:Atividades? = intent.getSerializableExtra(ITEM) as Atividades?
        if (item != null){
            novo_item.setText(item.nome)
        }


        salvaritem.setOnClickListener(){
                val item = Atividades(novo_item.text.toString())
                val abreLista = Intent(this, Lista::class.java)
                abreLista.putExtra(NovoItem, item)
                setResult(Activity.RESULT_OK, abreLista)
                finish()
        }
    }


}
