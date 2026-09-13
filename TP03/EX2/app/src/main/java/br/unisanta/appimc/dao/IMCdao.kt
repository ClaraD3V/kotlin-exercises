package br.unisanta.appimc.dao

import br.unisanta.appimc.model.IMC

class IMCdao {
    companion object{
        var IMC: IMC? = null
    }
    fun salvar(imc:Double,classificacao:String ){
        IMC = IMC(imc,classificacao)
    }

    fun buscar():IMC? = IMC
}