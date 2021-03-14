package br.com.eupedro.desafio_webservice.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.eupedro.desafio_webservice.api.ResponseApi
import br.com.eupedro.desafio_webservice.business.MarvelBusiness
import br.com.eupedro.desafio_webservice.model.ComicsList
import br.com.eupedro.desafio_webservice.utils.Constants.Api.BASE_COMICS
import kotlinx.coroutines.launch

class MarvelViewModel : ViewModel() {

    val comicsLiveData = MutableLiveData<ComicsList>()
    val comicsBusiness = MarvelBusiness()
    val errorMessageLiveData: MutableLiveData<String> = MutableLiveData()

    fun getComics() {
        viewModelScope.launch {
            val comics = comicsBusiness.getComics()
            notifyLiveData(comics, BASE_COMICS)
        }
    }

    private fun notifyLiveData(response: ResponseApi, liveData: String) {
        if (response is ResponseApi.Success) {
            when (liveData) {
                BASE_COMICS -> {
                    comicsLiveData.postValue(response.data as? ComicsList)
                }
            }
        } else {
            response as ResponseApi.Error
            errorMessageLiveData.postValue(response.message)
        }
    }
}