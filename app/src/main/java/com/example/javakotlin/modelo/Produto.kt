package com.example.javakotlin.modelo

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal

class Produto(
    val nome: String,
    val descricao: String,
    val valor: BigDecimal
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "", // Considerar se a string vazia é aceitável
        parcel.readString() ?: "", // O mesmo aqui
        BigDecimal(parcel.readString() ?: "0") // Verificar se a string é um número válido
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeString(descricao)
        parcel.writeString(valor.toString()) // Pode lançar exceção se BigDecimal não for válido
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Produto> {
        override fun createFromParcel(parcel: Parcel): Produto {
            return Produto(parcel)
        }

        override fun newArray(size: Int): Array<Produto?> {
            return arrayOfNulls(size)
        }
    }
}
