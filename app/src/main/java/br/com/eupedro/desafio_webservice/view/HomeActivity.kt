package br.com.eupedro.desafio_webservice.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.eupedro.desafio_webservice.R
import br.com.eupedro.desafio_webservice.utils.Constants.ComicsDetail.KEY_INTENT_COMIC_DATE
import br.com.eupedro.desafio_webservice.utils.Constants.ComicsDetail.KEY_INTENT_COMIC_DESC
import br.com.eupedro.desafio_webservice.utils.Constants.ComicsDetail.KEY_INTENT_COMIC_NUMBER
import br.com.eupedro.desafio_webservice.utils.Constants.ComicsDetail.KEY_INTENT_COMIC_PAGE
import br.com.eupedro.desafio_webservice.utils.Constants.ComicsDetail.KEY_INTENT_COMIC_PRICE
import br.com.eupedro.desafio_webservice.utils.Constants.ComicsDetail.KEY_INTENT_COMIC_THUMBNAIL
import br.com.eupedro.desafio_webservice.utils.Constants.ComicsDetail.KEY_INTENT_COMIC_TITLE
import br.com.eupedro.desafio_webservice.adapter.ComicsAdapter
import br.com.eupedro.desafio_webservice.viewModel.MarvelViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: MarvelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()
        iniComponents()
    }

    private fun iniComponents() {

        viewModel = ViewModelProvider(this).get(MarvelViewModel::class.java)
        viewModel.getComics()

        viewModel.comicsLiveData.observe(this){ marvel ->
            findViewById<RecyclerView>(R.id.rvListComics).apply {
                layoutManager = GridLayoutManager(
                    this@HomeActivity, 3,
                    GridLayoutManager.VERTICAL, false
                )
                adapter = ComicsAdapter(marvel.data.results) { position ->
                    val intent = Intent(this@HomeActivity, DetalheComicsActivity::class.java)
                    intent.putExtra(KEY_INTENT_COMIC_NUMBER,marvel.data.results[position].id)
                    intent.putExtra(KEY_INTENT_COMIC_TITLE,marvel.data.results[position].title)
                    intent.putExtra(KEY_INTENT_COMIC_DESC, marvel.data.results[position].description)

                    marvel.data.results[position].dates.forEach(){
                        if (it.type == "onsaleDate"){
                            intent.putExtra(KEY_INTENT_COMIC_DATE, it.date)
                        }
                    }

                    marvel.data.results[position].prices.forEach(){
                        if (it.type == "printPrice"){
                            intent.putExtra(KEY_INTENT_COMIC_PRICE, it.price)
                        }
                    }

                    intent.putExtra(KEY_INTENT_COMIC_PAGE,marvel.data.results[position].pageCount)
                    intent.putExtra(KEY_INTENT_COMIC_THUMBNAIL, "${marvel.data.results[position].thumbnail.path}.${marvel.data.results[position].thumbnail.extension}")
                    startActivity(intent)
                }

            }
        }
    }
}