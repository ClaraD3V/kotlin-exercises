package com.example.tp05

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tp05.viewmodel.FilmeViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

class FilmeViewModelTest {

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    @Test
    fun salvar_novo_filme_adiciona_na_lista() {
        val vm = FilmeViewModel()
        val antes = vm.filmes.value!!.size

        assertNull(vm.salvar(null, "  Matrix  ", 4.5f, ""))

        val lista = vm.filmes.value!!
        assertEquals(antes + 1, lista.size)
        assertEquals("Matrix", lista.last().nome)
    }

    @Test
    fun salvar_com_nome_vazio_retorna_erro_e_nao_altera_lista() {
        val vm = FilmeViewModel()
        val antes = vm.filmes.value!!.size

        assertNotNull(vm.salvar(null, "   ", 3f, ""))
        assertEquals(antes, vm.filmes.value!!.size)
    }

    @Test
    fun salvar_com_id_edita_nome_nota_e_foto() {
        val vm = FilmeViewModel()
        val id = vm.filmes.value!!.first().id

        assertNull(vm.salvar(id, "Novo nome", 2f, "https://exemplo.com/a.jpg"))

        val editado = vm.filmes.value!!.first { it.id == id }
        assertEquals("Novo nome", editado.nome)
        assertEquals(2f, editado.nota, 0f)
        assertEquals("https://exemplo.com/a.jpg", editado.foto)
    }

    @Test
    fun nota_fora_do_limite_e_ajustada() {
        val vm = FilmeViewModel()
        vm.salvar(null, "Teste", 9f, "")
        assertEquals(FilmeViewModel.NOTA_MAXIMA, vm.filmes.value!!.last().nota, 0f)
    }

    @Test
    fun excluir_remove_o_filme() {
        val vm = FilmeViewModel()
        val id = vm.filmes.value!!.first().id

        vm.excluir(id)

        assertEquals(false, vm.filmes.value!!.any { it.id == id })
    }
}
