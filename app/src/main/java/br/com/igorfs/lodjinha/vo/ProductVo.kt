package br.com.igorfs.lodjinha.vo

class ProductVo (
    val id: Long,
    val nome: String,
    val descricao: String,
    val precoDe: Double,
    val precoPor: Double,
    val urlImagem: String,
    val categoria: CategoryVo
)