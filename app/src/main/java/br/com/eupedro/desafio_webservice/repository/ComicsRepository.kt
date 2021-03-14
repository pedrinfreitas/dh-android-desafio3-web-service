package br.com.eupedro.desafio_webservice.repository

import br.com.eupedro.desafio_webservice.api.MarvelApiService.marvelServiceAPI
import br.com.eupedro.desafio_webservice.api.MarvelResponseValidation
import br.com.eupedro.desafio_webservice.api.ResponseApi

class ComicsRepository {

    val marvelService = marvelServiceAPI
    val responseValidation = MarvelResponseValidation()

    suspend fun getComics(): ResponseApi {
        return responseValidation.DefaultResponseValidation(
            marvelService.getComics(), "Erro na api"
        )
    }
}