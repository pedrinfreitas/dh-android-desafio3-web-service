package br.com.eupedro.desafio_webservice.api

import android.util.Log
import retrofit2.Response

class MarvelResponseValidation {

    //TODO Refactor or change logic for error handling and error messaging to user
    fun DefaultResponseValidation(serviceResponse: Response<*>, error: String): ResponseApi {
        lateinit var response: Response<*>
        return try {
            response = serviceResponse//TODO page number
            if (response.isSuccessful && response.body() != null) {
                ResponseApi.Success(response.body())
            } else {
                ResponseApi.Error("$error. Code: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e(
                "ERROR",
                "An error occurred trying to validate response from MoviesService. ${e.stackTrace}"
            )
            ResponseApi.Error(error)
        }
    }

}