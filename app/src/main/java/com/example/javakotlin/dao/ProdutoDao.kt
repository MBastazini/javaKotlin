package com.example.javakotlin.dao

import android.os.Parcel
import android.os.Parcelable
import com.example.javakotlin.modelo.Produto

class ProdutoDao(val produtos: MutableList<Produto>) : Parcelable {

    // Construtor secundário que lê os dados do Parcel
    constructor(parcel: Parcel) : this(
        mutableListOf<Produto>().apply {
            parcel.readTypedList(this, Produto.CREATOR)
        }
    )

    // Método para adicionar um produto
    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    // Método para buscar todos os produtos
    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    // Escreve os dados no Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(produtos)
    }

    // Descreve os conteúdos (0 por padrão)
    override fun describeContents(): Int {
        return 0
    }

    // Companion object para criar instâncias de ProdutoDao a partir de um Parcel
    companion object CREATOR : Parcelable.Creator<ProdutoDao> {
        override fun createFromParcel(parcel: Parcel): ProdutoDao {
            return ProdutoDao(parcel)
        }

        override fun newArray(size: Int): Array<ProdutoDao?> {
            return arrayOfNulls(size)
        }
    }
}
