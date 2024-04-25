package com.example.tedoexperiment.data

//import com.example.tedoexperiment.api.ApiClient
//import com.example.tedoexperiment.api.ApiService
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tedoexperiment.api.RetroServiceInterface
import com.example.tedoexperiment.api.RetrofitInstance
import okhttp3.Callback
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import kotlin.concurrent.thread

class MainActivityViewModel(val fileDir: File) : ViewModel() {
    private var pdfFileName: File
    private var dirPath: String
    private var fileName: String
    var isFileReadyObserver = MutableLiveData<Boolean>()

    init {
        dirPath = "${fileDir}/myfolder/pdffiles"
        val dirFile = File(dirPath)
        if (!dirFile.exists()) {
            dirFile.mkdir()
        }
        fileName = ""
        val file = "${dirPath}/${fileName}"
        pdfFileName = File(file)
        if (pdfFileName.exists()) {
            pdfFileName.delete()
        }
    }
    fun getPdfFileUri(): File = pdfFileName

    fun downloadPdfFile(pdfUrl: String) {
        thread {
            val retroServiceInterface =
                RetrofitInstance.getRetroInstance().create(RetroServiceInterface::class.java)
            retroServiceInterface.downloadPdfFile(pdfUrl)
                .enqueue(object : retrofit2.Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        isFileReadyObserver.postValue(false)

                    }

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            isFileReadyObserver.postValue(true)
                            val result = response.body()?.byteStream()
                            result?.let {
                                writeToFile(it)

                            } ?: kotlin.run {
                                isFileReadyObserver.postValue(false)
                            }
                        } else
                            isFileReadyObserver.postValue(false)

                    }
                })
        }
    }


    private fun writeToFile(inputStream: InputStream) {
        try {
            Log.e("====", "====writeToFile : ")
            val fileReader = ByteArray(4096)
            var fileSizeDownloaded = 0
            val fos: OutputStream = FileOutputStream(pdfFileName)
            do {
                val read = inputStream.read(fileReader)
                if (read != -1) {
                    fos.write(fileReader, 0, read)
                    fileSizeDownloaded += read
                }
            } while (read != -1)
            fos.flush()
            fos.close()
            isFileReadyObserver.postValue(true)
        } catch (e: IOException) {
            Log.e("====", "====IOException : " + e)
            isFileReadyObserver.postValue(false)
        }
    }
}

