package br.universidade.approomproduto.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import br.universidade.approomproduto.dao.ProductDao
import br.universidade.approomproduto.databinding.ActivityMainBinding
import br.universidade.approomproduto.db.AppDatabase
import br.universidade.approomproduto.model.Product
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var productDao: ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o banco de dados Room de forma recomendada (sem usar allowMainThreadQueries)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        )
            .fallbackToDestructiveMigration() // Usado para recriar o DB se o esquema mudar
            .build()
        productDao = db.productDao()

        // Exemplo de inserção de produto
        binding.btnInserirProduto.setOnClickListener {
            val name = binding.editProductName.text.toString()
            val priceText = binding.editProductPrice.text.toString().replace(',', '.')
            val description = binding.editProductDescription.text.toString()
            val quantity = binding.editProductQuantity.text.toString().toIntOrNull() ?: 0

            if (name.isEmpty()) {
                Toast.makeText(this, "Preencha os campos obrigatórios!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = priceText.toDoubleOrNull()
            if (price == null || price <= 0.0) {
                binding.editProductPrice.error = "Informe um preço válido"
                return@setOnClickListener
            }

            val product = Product(name = name, price = price, description = description, quantity = quantity)
            // Insere o produto no banco de dados de forma assíncrona (usando corrotina)
            lifecycleScope.launch {
                productDao.insertAll(product)
                Log.i("TAG", "Produto inserido: $name - R$$price")
                Toast.makeText(this@MainActivity, "Produto inserido!", Toast.LENGTH_SHORT).show()
                binding.editProductName.text.clear()
                binding.editProductPrice.text.clear()
                binding.editProductDescription.text.clear()
                binding.editProductQuantity.text.clear()
                binding.editProductName.requestFocus()
            }
        }

        // Exemplo de recuperação de produtos
        binding.btnListarProdutos.setOnClickListener {
            // Recupera os produtos de forma assíncrona
            lifecycleScope.launch {
                val products = productDao.getAll()
                if (products.isNotEmpty()) {
                    products.forEach {
                        Log.i("TAG", "Produto = ${it.name}, Preço = R$${it.price}, Descrição = ${it.description}")
                    }
                } else {
                    Log.i("TAG", "Nenhum produto encontrado.")
                }
                mostrarProdutos(products)
            }
        }

        binding.btnBuscar.setOnClickListener {
            val texto = binding.editBuscar.text.toString().trim()
            lifecycleScope.launch {
                mostrarProdutos(productDao.buscarPorNome("%$texto%"))
            }
        }

        binding.btnExcluirTodos.setOnClickListener {
            lifecycleScope.launch {
                productDao.deleteAll()
                Toast.makeText(this@MainActivity, "Produtos excluídos!", Toast.LENGTH_SHORT).show()
                mostrarProdutos(emptyList())
            }
        }

        binding.btnAtualizarPreco.setOnClickListener {
            val id = binding.editUpdateId.text.toString().toIntOrNull()
            val newPrice = binding.editUpdatePrice.text.toString().replace(',', '.').toDoubleOrNull()

            if (id == null) {
                binding.editUpdateId.error = "Informe o id"
                return@setOnClickListener
            }
            if (newPrice == null || newPrice <= 0.0) {
                binding.editUpdatePrice.error = "Informe um preço válido"
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val product = productDao.getById(id)
                if (product == null) {
                    Toast.makeText(this@MainActivity, "Produto não encontrado!", Toast.LENGTH_SHORT).show()
                } else {
                    productDao.update(product.copy(price = newPrice))
                    Toast.makeText(this@MainActivity, "Preço atualizado!", Toast.LENGTH_SHORT).show()
                    binding.editUpdateId.text.clear()
                    binding.editUpdatePrice.text.clear()
                    mostrarProdutos(productDao.getAll())
                }
            }
        }
    }

    private fun mostrarProdutos(products: List<Product>) {
        binding.txtLista.text = if (products.isEmpty()) {
            "Nenhum produto encontrado."
        } else {
            products.joinToString("\n") {
                "${it.id} - ${it.name} - R$ ${"%.2f".format(it.price)} - Qtd: ${it.quantity} - ${it.description}"
            }
        }
        binding.txtTotal.text =
            "Produtos: ${products.size} | Soma dos preços: R$ ${"%.2f".format(products.sumOf { it.price })}"
    }
}
