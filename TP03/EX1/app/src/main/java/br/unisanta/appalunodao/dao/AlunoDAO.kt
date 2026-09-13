package br.unisanta.appalunodao.dao

import br.unisanta.appalunodao.model.Aluno

class AlunoDAO {
    companion object {
        var aluno:Aluno? = null
    }
    fun salvar(nome:String,turma:String ){
        aluno = Aluno(nome,turma)
    }

    fun buscar():Aluno? = aluno
}