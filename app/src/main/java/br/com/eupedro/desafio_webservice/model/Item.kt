package br.com.eupedro.desafio_webservice.model

data class Item(
    val name: String,
    val resourceURI: String,
    val role: String?,
    val type: String?
)