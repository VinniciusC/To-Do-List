package com.carvalho.vinicius.todolist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.item_lista.view.*

class ItemAdapter(val itens: List<Atividades>)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var clique: ((index: Int) -> Unit)? = null
    var cliqueFeito: ((index: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Atividades = itens[position]
        holder.bindView(item, clique, cliqueFeito)
    }

    fun configuraClique(clique: ((index: Int) -> Unit)) {
        this.clique = clique
    }
    fun configuraCliqueFeito(cliqueFeito: ((index: Int) -> Unit)){
        this.cliqueFeito = cliqueFeito
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: Atividades, clique: ((index: Int) -> Unit)?, cliqueFeito: ((index: Int) -> Unit)?) {
            itemView.itemNome.text = item.nome

            if (clique != null) {
                itemView.setOnClickListener {
                    clique.invoke(adapterPosition)
                }
            }
            if(cliqueFeito != null){
                itemView.feitoBTN.setOnClickListener{
                    cliqueFeito.invoke(adapterPosition)
                }
            }

        }
    }
}