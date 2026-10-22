package com.example.tp05.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp05.model.Filme

/**
 * VIEWMODEL: guarda o estado da tela (lista de filmes) e a lógica de edição.
 * A View apenas observa [filmes] e chama [salvar] / [excluir].
 */
class FilmeViewModel : ViewModel() {

    companion object {
        const val NOTA_MAXIMA = 5f
    }

    private var proximoId = 1L
    private val lista = mutableListOf<Filme>()

    private val _filmes = MutableLiveData<List<Filme>>(emptyList())
    val filmes: LiveData<List<Filme>> = _filmes

    init {
        // Dados iniciais de exemplo (sem foto: a View mostra um placeholder).
        lista.add(Filme(proximoId++, "O Poderoso Chefão", 5f))
        lista.add(Filme(proximoId++, "Interestelar", 4.5f))
        lista.add(Filme(proximoId++, "Cidade de Deus", 4.5f))
        _filmes.value = lista.toList()
    }

    /**
     * Cria (id == null) ou edita (id != null) um filme.
     * @return mensagem de erro, ou null quando salvou com sucesso.
     */
    fun salvar(id: Long?, nome: String, nota: Float, foto: String): String? {
        val nomeLimpo = nome.trim()
        if (nomeLimpo.isEmpty()) return "Informe o nome do filme"

        val notaValida = nota.coerceIn(0f, NOTA_MAXIMA)
        val fotoLimpa = foto.trim()

        if (id == null) {
            lista.add(Filme(proximoId++, nomeLimpo, notaValida, fotoLimpa))
        } else {
            val posicao = lista.indexOfFirst { it.id == id }
            if (posicao < 0) return "Filme não encontrado"
            lista[posicao] = lista[posicao].copy(nome = nomeLimpo, nota = notaValida, foto = fotoLimpa)
        }

        publicar()
        return null
    }

    fun excluir(id: Long) {
        if (lista.removeAll { it.id == id }) publicar()
    }

    // Publica uma CÓPIA da lista: o DiffUtil do adapter compara a lista antiga com a nova.
    private fun publicar() {
        _filmes.value = lista.toList()
    }
}
