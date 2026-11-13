package br.unisanta.desafio.dao

import br.unisanta.desafio.model.Login

class LoginDAO {

    companion object {
        var login: Login? = null
    }
    fun validar(usuario:String,senha:String ):Boolean{
        val login = Login()
        return (usuario == login.usuario && senha == login.senha)
    }
}