package com.developer27.xumorous.viewModels
import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MemeViewModel : ViewModel() {


    fun getMemeData(context: Context, memeImageView: ImageView) {
        val apiUrl = "https://api.humorapi.com/memes/random?api-key=5d143694632a49819f490e33e48b3f02"
        val requestQueue = Volley.newRequestQueue(context)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, apiUrl, null,
            Response.Listener<JSONObject> { response ->
                    val imageUrl = response.getString("url")
                    loadImage(context, imageUrl, memeImageView)
            },
            Response.ErrorListener { error ->
                // Handle other network errors if needed
                showSnackbar(context, error.message ?: "Unknown error")
            })

        requestQueue.add(jsonObjectRequest)
    }

    private fun loadImage(context: Context, imageUrl: String, memeImage: ImageView) {
        val imageRequest = ImageRequest(
            imageUrl,
            Response.Listener { response ->
                memeImage.setImageBitmap(response)
            },
            0,
            0,
            ImageView.ScaleType.CENTER_INSIDE,
            null,
            Response.ErrorListener { error ->
                // Write a code to show snackbar when there is an error
                showSnackbar(context, error.message ?: "Unknown error")
            }
        )
        Volley.newRequestQueue(context).add(imageRequest)
    }

    private fun showSnackbar(context: Context, message: String) {
        // Write code to display a Snackbar with the error message
        val rootView: View = (context as AppCompatActivity).findViewById(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
    }

    // Method to clear cache
    public fun clearCache(context: Context) {
        Volley.newRequestQueue(context).cache.clear()
    }
}