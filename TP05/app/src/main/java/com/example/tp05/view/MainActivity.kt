package com.example.tp05.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp05.R
import com.example.tp05.databinding.ActivityMainBinding
import com.example.tp05.databinding.DialogFilmeBinding
import com.example.tp05.model.Filme
import com.example.tp05.viewmodel.FilmeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: FilmeViewModel by viewModels()

    private val adapter = FilmeAdapter(
        aoEditar = { filme -> mostrarDialogo(filme) },
        aoExcluir = { filme -> confirmarExclusao(filme) },
    )

    private var dialogBinding: DialogFilmeBinding? = null

    private val escolherFoto = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        if (uri != null) {
            try {
                contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            } catch (_: SecurityException) {

            }
            dialogBinding?.edtFoto?.setText(uri.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvFilmes.layoutManager = LinearLayoutManager(this)
        binding.rvFilmes.adapter = adapter

        viewModel.filmes.observe(this) { filmes ->
            adapter.submitList(filmes)
            binding.txtVazio.isVisible = filmes.isEmpty()
        }

        binding.fabAdicionar.setOnClickListener { mostrarDialogo(null) }
    }

    private fun mostrarDialogo(filme: Filme?) {
        val dlg = DialogFilmeBinding.inflate(layoutInflater)
        dialogBinding = dlg

        dlg.edtFoto.doAfterTextChanged { dlg.imgPreview.carregarFoto(it?.toString()) }
        dlg.edtNome.setText(filme?.nome.orEmpty())
        dlg.ratingNota.rating = filme?.nota ?: 0f
        dlg.edtFoto.setText(filme?.foto.orEmpty())
        dlg.imgPreview.carregarFoto(filme?.foto)

        dlg.btnGaleria.setOnClickListener { escolherFoto.launch(arrayOf("image/*")) }

        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle(if (filme == null) R.string.novo_filme else R.string.editar_filme)
            .setView(dlg.root)
            .setNegativeButton(R.string.cancelar, null)
            .setPositiveButton(R.string.salvar, null) // clique definido abaixo p/ não fechar com erro
            .setOnDismissListener { dialogBinding = null }
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val erro = viewModel.salvar(
                    id = filme?.id,
                    nome = dlg.edtNome.text.toString(),
                    nota = dlg.ratingNota.rating,
                    foto = dlg.edtFoto.text.toString(),
                )
                if (erro == null) dialog.dismiss() else dlg.tilNome.error = erro
            }
        }
        dialog.show()
    }

    private fun confirmarExclusao(filme: Filme) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.excluir_titulo)
            .setMessage(getString(R.string.excluir_mensagem, filme.nome))
            .setNegativeButton(R.string.cancelar, null)
            .setPositiveButton(R.string.excluir) { _, _ -> viewModel.excluir(filme.id) }
            .show()
    }
}
