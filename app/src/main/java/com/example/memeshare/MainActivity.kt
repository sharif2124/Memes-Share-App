package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.memeshare.databinding.ActivityMainBinding
import com.android.volley.toolbox.JsonObjectRequest as Str

class MainActivity : AppCompatActivity() {


private lateinit var binding: ActivityMainBinding
var currentImageurl:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadmeme()
    }

    private fun loadmeme(){
       binding.progressBar.visibility=View.VISIBLE

        val url= "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = Str(Request.Method.GET, url, null,

            Response.Listener { response ->
                currentImageurl  = response.getString("url")
                Glide.with(this).load(currentImageurl).listener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                       binding.progressBar.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility=View.GONE
                        return false
                    }

                }).into(binding.memeImageView)
            },
            Response.ErrorListener { error ->

            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    fun next(view: View) {
        loadmeme()
    }

    fun share(view: View) {
      val intent = Intent(Intent.ACTION_SEND)
        intent.type="text/plane"
        intent.putExtra(Intent.EXTRA_TEXT,"$currentImageurl")
        val chooser = Intent.createChooser(intent,"Share the meme using...")
        startActivity(chooser)
    }
}