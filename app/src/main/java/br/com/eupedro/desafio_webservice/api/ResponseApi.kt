package br.com.eupedro.desafio_webservice.api

sealed class ResponseApi {
    class Success(val data: Any?) : ResponseApi()
    class Error(val message: String) : ResponseApi()
}