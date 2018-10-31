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

        val item = intent.getStringExtra(ITEM)
        print(item)
        novo_item.setText(item)

        salvaritem.setOnClickListener(){
                val abreLista = Intent(this, Lista::class.java)
                abreLista.putExtra(NovoItem, novo_item.text.toString())
                setResult(Activity.RESULT_OK, abreLista)
                finish()
        }
    }


}
