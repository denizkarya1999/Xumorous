package com.developer27.xumorous.viewModels

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject

class JokeViewModel : ViewModel() {
    // Setup data
    private val _setup = MutableLiveData<String>()
    val setup: LiveData<String> = _setup

    // Punchline data
    private val _punchline = MutableLiveData<String>()
    val punchline: LiveData<String> = _punchline

    // Method to get joke from server
    fun getJokeDataAPI(context: Context) {
        val apiUrl = "https://official-joke-api.appspot.com/random_joke"
        val requestQueue = Volley.newRequestQueue(context)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, apiUrl, null,
            Response.Listener<JSONObject> { response ->
                // Parse the JSON response and set the joke
                setJoke(response)
            },
            Response.ErrorListener { error ->
                val errorMessage = error.message ?: "Unknown error"
                showSnackbar(context, errorMessage)
            })

        Volley.newRequestQueue(context).add(jsonObjectRequest)
    }

    // Method to set joke
    private fun setJoke(data: JSONObject) {
        _setup.value = data.getString("setup")
        _punchline.value = data.getString("punchline")
    }

    // Method to clear cache
    public fun clearCache(context: Context) {
        Volley.newRequestQueue(context).cache.clear()
    }

    private fun showSnackbar(context: Context, message: String) {
        // Show Snackbar on the main thread
        Handler(Looper.getMainLooper()).post {
            val rootView: View = (context as AppCompatActivity).findViewById(android.R.id.content)
            Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}