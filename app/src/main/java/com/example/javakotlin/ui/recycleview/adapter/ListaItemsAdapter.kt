package com.example.javakotlin.ui.recycleview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.javakotlin.R
import com.example.javakotlin.modelo.Produto

class ListaItemsAdapter(
    private val context: Context,
    private val produtos: List<Produto>,
) : RecyclerView.Adapter<ListaItemsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(produto: Produto) {

            val nome = itemView.findViewById<TextView>(R.id.nome);
            val descricao = itemView.findViewById<TextView>(R.id.descricao);
            val valor = itemView.findViewById<TextView>(R.id.valor);

            nome.text = produto.nome
            descricao.text = produto.descricao
            valor.text = produto.valor.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }



}
