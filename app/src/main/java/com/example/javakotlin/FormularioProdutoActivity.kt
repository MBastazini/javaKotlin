package com.example.javakotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.javakotlin.dao.ProdutoDao
import com.example.javakotlin.modelo.Produto
import com.example.javakotlin.ui.theme.JavaKotlinTheme
import java.math.BigDecimal


class FormularioProdutoActivity : ComponentActivity(R.layout.formulario_produto_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = intent.getParcelableExtra<ProdutoDao>("dao")

        if (dao != null) {
            val botaoSalvar = findViewById<Button>(R.id.botao_salvar)
            botaoSalvar.setOnClickListener {
                val campoNome = findViewById<EditText>(R.id.nome)
                val nome = campoNome.text.toString()
                val campoDescricao = findViewById<EditText>(R.id.descricao)
                val descricao = campoDescricao.text.toString()
                val campoValor = findViewById<EditText>(R.id.valor)
                val valorEmTexto = campoValor.text.toString()
                val valor = if (valorEmTexto.isBlank()) {
                    BigDecimal.ZERO
                } else {
                    BigDecimal(valorEmTexto)
                }

                val produtoNovo = Produto(
                    nome = nome,
                    descricao = descricao,
                    valor = valor
                )

                Log.i("FormularioProduto", "onCreate: $produtoNovo")
                dao.adiciona(produtoNovo)

                // Retorna o dao atualizado
                val resultIntent = Intent()
                resultIntent.putExtra("dao", dao)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        } else {
            finish()
        }
    }
}


@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JavaKotlinTheme {
        Greeting2("MY FRIENDO")
    }
}