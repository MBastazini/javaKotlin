package com.example.javakotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.RecyclerView
import com.example.javakotlin.dao.ProdutoDao
import com.example.javakotlin.ui.theme.JavaKotlinTheme
import com.example.javakotlin.modelo.Produto
import com.example.javakotlin.ui.recycleview.adapter.ListaItemsAdapter
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    private lateinit var adapter: ListaItemsAdapter
    private lateinit var dao: ProdutoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialize o RecyclerView e o ProdutoDao
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        dao = ProdutoDao(mutableListOf())

        // Adicione um item inicial para teste
        dao.adiciona(
            Produto(
                nome = "JORGE",
                descricao = "AMADO",
                valor = BigDecimal(1)
            )
        )

        // Inicialize o adapter
        adapter = ListaItemsAdapter(context = this, produtos = dao.buscaTodos())
        recyclerView.adapter = adapter

        // Configuração do botão para abrir a outra atividade
        val new = findViewById<Button>(R.id.button)
        new.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            intent.putExtra("dao", dao)
            startActivityForResult(intent, REQUEST_CODE_FORMULARIO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_FORMULARIO && resultCode == RESULT_OK) {
            // Obtém o dao atualizado
            val updatedDao = data?.getParcelableExtra<ProdutoDao>("dao")
            if (updatedDao != null) {
                dao = updatedDao
                adapter = ListaItemsAdapter(context = this, produtos = dao.buscaTodos())
                // Atualiza o RecyclerView com a nova lista
                findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_FORMULARIO = 1
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JavaKotlinTheme {
        Greeting("GAYER")
    }
}
