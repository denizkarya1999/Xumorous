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

class InsultViewModel : ViewModel() {
    // Insult related data
    private val _insult = MutableLiveData<String>()
    val insult: LiveData<String> = _insult

    // Method to get insult from server
    fun getInsultDataAPI(context: Context) {
        val apiUrl = "https://evilinsult.com/generate_insult.php?lang=en&type=json"
        val requestQueue = Volley.newRequestQueue(context)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, apiUrl, null,
            Response.Listener<JSONObject> { response ->
                // Parse the JSON response and set the insult
                val newInsult = response.getString("insult")
                setInsult(newInsult)
            },
            Response.ErrorListener { error ->
                // Pass the error message to the callback
                val errorMessage = error.message ?: "Unknown error"
                showSnackbar(context, errorMessage)
            })

        requestQueue.add(jsonObjectRequest)
    }

    // Method to set insult
    private fun setInsult(newInsult: String) {
        _insult.value = newInsult
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