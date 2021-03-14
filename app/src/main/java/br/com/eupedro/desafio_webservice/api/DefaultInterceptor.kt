package br.com.eupedro.desafio_webservice.api

import br.com.eupedro.desafio_webservice.utils.Constants.Api.API_HASHKEY_NAME
import br.com.eupedro.desafio_webservice.utils.Constants.Api.API_KEY_NAME
import br.com.eupedro.desafio_webservice.utils.Constants.Api.API_KEY_VALUE
import br.com.eupedro.desafio_webservice.utils.Constants.Api.API_PRIVATEKEY_VALUE
import br.com.eupedro.desafio_webservice.utils.Constants.Api.API_TS_NAME
import br.com.eupedro.desafio_webservice.utils.Constants.Api.API_TS_VALUE
import br.com.eupedro.desafio_webservice.utils.Constants.Api.HEADER_CONTENT_FIELD
import br.com.eupedro.desafio_webservice.utils.Constants.Api.HEADER_CONTENT_TYPE
import br.com.eupedro.desafio_webservice.utils.Constants.Api.QUERY_FORMATTYPE_NAME
import br.com.eupedro.desafio_webservice.utils.Constants.Api.QUERY_FORMATTYPE_VALUE
import br.com.eupedro.desafio_webservice.utils.Constants.Api.QUERY_FORMAT_NAME
import br.com.eupedro.desafio_webservice.utils.Constants.Api.QUERY_FORMAT_VALUE
import br.com.eupedro.desafio_webservice.utils.Constants.Api.QUERY_ORDERBY_NAME
import br.com.eupedro.desafio_webservice.utils.Constants.Api.QUERY_ORDERBY_VALUE
import br.com.eupedro.desafio_webservice.utils.Constants.Api.QUERY_STARTYEAR_NAME
import br.com.eupedro.desafio_webservice.utils.Constants.Api.QUERY_STARTYEAR_VALUE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object DefaultInterceptor {
    val defaultInterceptor = getInterceptorClient()

    private fun getInterceptorClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader(HEADER_CONTENT_FIELD, HEADER_CONTENT_TYPE)
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor { chain ->
                val url = chain.request().url().newBuilder()
                    .addQueryParameter(API_TS_NAME, API_TS_VALUE)
                    .addQueryParameter(API_KEY_NAME, API_KEY_VALUE)
                    .addQueryParameter(API_HASHKEY_NAME, API_PRIVATEKEY_VALUE)
                    .addQueryParameter(QUERY_FORMAT_NAME, QUERY_FORMAT_VALUE)
                    .addQueryParameter(QUERY_FORMATTYPE_NAME, QUERY_FORMATTYPE_VALUE)
                    .addQueryParameter(QUERY_ORDERBY_NAME, QUERY_ORDERBY_VALUE)
                    .addQueryParameter(QUERY_STARTYEAR_NAME, QUERY_STARTYEAR_VALUE)
                    .build()
                val newRequest = chain.request().newBuilder().url(url).build()
                chain.proceed(newRequest)
            }

        return interceptor.build()
    }
}