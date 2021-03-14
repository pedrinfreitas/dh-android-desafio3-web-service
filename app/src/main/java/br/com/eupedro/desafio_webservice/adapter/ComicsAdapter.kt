package br.com.eupedro.desafio_webservice.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import br.com.eupedro.desafio_webservice.R
import br.com.eupedro.desafio_webservice.model.Result

class ComicsAdapter(private val comicList : List<Result>,
                    private val onItemClicked : (Int) -> Unit
) : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_grid_poster,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comicList[position],onItemClicked)
    }

    override fun getItemCount(): Int {
        return comicList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private lateinit var ivPosterComic : ImageView
        private lateinit var tvPosterComic : TextView

        fun bind(list : Result, onItemMenuClicked: (Int) -> Unit) = with(itemView) {

            ivPosterComic = findViewById<ImageView>(R.id.ivPosterComic)
            tvPosterComic = findViewById<TextView>(R.id.tvPosterComic)

            Log.i("ViewHolder","${list.thumbnail.path.replace("http:","https:")}.${list.thumbnail.extension}")

            Glide.with(itemView.context)
                .load("${list.thumbnail.path.replace("http:","https:")}.${list.thumbnail.extension}")
                .centerCrop()
                .into(ivPosterComic)
            ("#" + list.id).also { tvPosterComic.text = it }

            ivPosterComic.setOnClickListener {
                onItemMenuClicked(this@ViewHolder.adapterPosition)
            }

        }
    }
}
