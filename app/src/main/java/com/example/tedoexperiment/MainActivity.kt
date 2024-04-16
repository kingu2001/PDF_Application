package com.example.tedoexperiment

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.tedoexperiment.api.ApiClient
import com.example.tedoexperiment.data.TestDocumentResponse
import com.example.tedoexperiment.databinding.ActivityMainBinding
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val client = ApiClient.apiService.fetchTestDocuments("1")
        client.enqueue(
            object : Callback<TestDocumentResponse> {

                override fun onResponse(
                    callback: Callback,
                    response: Response<TestDocumentResponse>
                ){
                    if(response.isSuccessful){
                        Log.d("testDocuments", ""+response.body())
                    }
                }

                override fun onFailure(
                    callback: Callback,
                    t : Throwable
                ){
                    Log.e("failed", ""+t.message)
                }
            }
        )

        binding.floatingActionButton.setOnClickListener {
            // Load PDF from the assets folder
            val assetFileName = "CV.pdf" // Replace with your PDF file name
            loadPDFFromAssets(assetFileName)
        }
    }

    private fun loadPDFFromAssets(fileName: String) {
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
    }
}