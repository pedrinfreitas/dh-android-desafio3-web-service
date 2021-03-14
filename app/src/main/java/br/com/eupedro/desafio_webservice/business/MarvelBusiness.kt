package br.com.eupedro.desafio_webservice.business

import br.com.eupedro.desafio_webservice.api.ResponseApi
import br.com.eupedro.desafio_webservice.repository.ComicsRepository

class MarvelBusiness {

    private val comicsRepository: ComicsRepository by lazy {
        ComicsRepository()
    }

    suspend fun getComics(): ResponseApi {
        return comicsRepository.getComics()
    }


}