package br.com.amedigital.lojinha.model

import java.io.Serializable

class ProdutoResponse(id: Int, nome: String, urlImagem: String,
                      descricao: String, precoDe: Double, precoPor: Double,
                      categorias: List<CategoriaResponse>): Serializable