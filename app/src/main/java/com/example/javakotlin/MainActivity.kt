package com.example.javakotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.RecyclerView
import com.example.javakotlin.ui.theme.JavaKotlinTheme
import com.example.javakotlin.modelo.Produto
import com.example.javakotlin.ui.recycleview.adapter.ListaItemsAdapter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);
        recyclerView.adapter = ListaItemsAdapter(context = this, produtos = listOf(
            Produto(
                nome = "Ola!!!",
                descricao = "Soy JORGE!",
                valor = 1,
            ),
            Produto(
                nome = "Ola2",
                descricao = "Soy JORGE2!",
                valor = 2,
            ),
            Produto(
                nome = "Ola",
                descricao = "Soy JORGE2!",
                valor = 3,
            )
        ))
        /*
        enableEdgeToEdge()
        setContent {
            JavaKotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        } */
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
        Greeting("Android")
    }
}