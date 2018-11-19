package com.carvalho.vinicius.todolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_novo_item.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NovoItem : AppCompatActivity() {

    companion object {
        const val NovoItem: String = "NovoItem"
        const val ITEM = "Item"
    }

    var item: Atividades? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_item)

        item = intent.getSerializableExtra(ITEM) as Atividades?
        novo_item.setText(item?.nome)



        salvaritem.setOnClickListener(){
                if(item == null){
                    item = Atividades(novo_item.text.toString())
                }else{
                    item?.nome = novo_item.text.toString()
                }

            val itemDao: ToDoDAO = AppDatabase.getInstance(this).ToDoDAO()
            doAsync {
                itemDao.insert(item!!)
                uiThread {
                    finish()}
            }
        }
    }


}
