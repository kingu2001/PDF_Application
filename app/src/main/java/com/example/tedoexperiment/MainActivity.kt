package com.example.tedoexperiment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tedoexperiment.api.ApiClient
import com.example.tedoexperiment.data.TestDocumentResponse
import com.example.tedoexperiment.databinding.ActivityMainBinding
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        
    }

    private fun initViewModel()









        /*
        val client = ApiClient.apiService.fetchTestDocument("1")
        client.enqueue(
            object : retrofit2.Callback<ResponseBody> { // Specify ResponseBody as type parameter

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val inputStream =
                            response.body()?.byteStream() ?: return // Handle empty response
                        // ... (rest of your code to display PDF)
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // Handle failures
                }
            })

        binding.floatingActionButton.setOnClickListener {
            // Load PDF from the assets folder
            val documentId = "1"
            loadPDFFromAssets(documentId)
        }

    }*/
    /*private fun loadPDFFromAssets(documentId: String) {
        try {
            val call = ApiClient.apiService.fetchTestDocument(documentId)
            val response = call.execute()

            if (response.isSuccessful) {
                val inputStream = response.body()?.byteStream() ?: return // Handle empty response
                val uri = Uri.parse(inputStream.toString())
                binding.pdfView.fromUri(uri)
                    .spacing(12)
                    .defaultPage(0)
                    .enableAnnotationRendering(false)
                    .enableDoubletap(true)
                    .load()
                binding.pdfView.fitToWidth()
                binding.pdfView.useBestQuality(true)
            } else {
                // Handle unsuccessful response
            }
        } catch (e: Exception) {
            // Handle exceptions
        }
    }*/

    /*private fun loadPDFFromAssets(fileName: String) {
        try {
            val inputStream = assets.open(fileName)
            binding.pdfView.fromStream(inputStream)
                .spacing(12)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .enableDoubletap(true)
                .load()
            binding.pdfView.fitToWidth()
            binding.pdfView.useBestQuality(true)
        } catch (e: Exception) {
            // Handle exceptions
        }
    }*/
}