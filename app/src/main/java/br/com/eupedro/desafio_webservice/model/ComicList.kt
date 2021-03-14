package br.com.eupedro.desafio_webservice.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicList(
    val comicImage: Int,
    val comicNumber: Int
) : Parcelable