package br.com.eupedro.desafio_webservice.view

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import br.com.eupedro.desafio_webservice.R
import br.com.eupedro.desafio_webservice.databinding.ActivityCoverDetalheBinding
import br.com.eupedro.desafio_webservice.utils.Constants.ComicsDetail.KEY_INTENT_COMIC_NUMBER
import br.com.eupedro.desafio_webservice.utils.Constants.ComicsDetail.KEY_INTENT_COMIC_THUMBNAIL
import com.bumptech.glide.Glide

class CoverDetalheActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoverDetalheBinding
    var comicNumber: Int = 0
    var comicThumbnail = ""

    private val ivCoverPosterComic: ImageView by lazy {
        findViewById(R.id.ivCoverPosterComic)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cover_detalhe)
        binding = ActivityCoverDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        comicNumber = intent.getIntExtra(KEY_INTENT_COMIC_NUMBER, 0)
        comicThumbnail = intent.getStringExtra(KEY_INTENT_COMIC_THUMBNAIL).toString()

        Glide.with(ivCoverPosterComic.context)
            .load(comicThumbnail.replace("http:", "https:"))
            .centerInside()
            .into(ivCoverPosterComic)

        binding.btnCoverVoltar.setOnClickListener {
            finish()
        }
    }
}