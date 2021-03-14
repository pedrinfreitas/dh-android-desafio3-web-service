package br.com.eupedro.desafio_webservice.api

import br.com.eupedro.desafio_webservice.model.ComicsList
import br.com.eupedro.desafio_webservice.utils.Constants.Api.BASE_COMICS
import retrofit2.Response
import retrofit2.http.GET

interface MarvelApi {

    @GET(BASE_COMICS)
    suspend fun getComics(): Response<ComicsList>

}