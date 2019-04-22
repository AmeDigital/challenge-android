package br.com.amedigital.network

import br.com.amedigital.network.model.BodyRequestBanner
import br.com.amedigital.network.model.BodyRequestCategory
import br.com.amedigital.network.model.BodyRequestProduct
import retrofit2.Call

class LojinhaServiceEndPoint : RestClient() {

    fun getBanner() : Call<BodyRequestBanner> {
        val service = LojinhaServiceEndPoint().createService(LojinhaService::class.java)
        return service.getBanner()
    }

    fun getCategoria() : Call<BodyRequestCategory> {
        val service = LojinhaServiceEndPoint().createService(LojinhaService::class.java)
        return service.getCategoria()
    }

    fun getProdutoPorCategoria(categoryId : Int) : Call<BodyRequestProduct> {
        val service = LojinhaServiceEndPoint().createService(LojinhaService::class.java)
        return service.getProdutoPorCategoria(categoryId)
    }

    fun getProdutosMaisVendidos() : Call<BodyRequestProduct> {
        val service = LojinhaServiceEndPoint().createService(LojinhaService::class.java)
        return service.getProdutosMaisVendidos()
    }

    fun setReserva(produtoId : Int) : Call<Void> {
        val service = LojinhaServiceEndPoint().createService(LojinhaService::class.java)
        return service.setReserva(produtoId)
    }
}