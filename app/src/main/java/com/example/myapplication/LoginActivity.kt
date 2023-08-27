package com.example.myapplication

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL


class LoginActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbnLogin.setOnClickListener {
            MyTaskTest().execute()
            var progressDialog=ProgressDialog(this)
            progressDialog.setMessage("Sabar Bre")
            progressDialog.setCancelable(false)
        }
    }

    @Suppress("DEPRECATION")
    class MyTaskTest():AsyncTask<String,String,String>() {
        constructor(parcel: Parcel) : this() {

        }

        override fun onPreExecute() {
            super.onPreExecute()


        }

        override fun doInBackground(vararg p0: String?): String {
            var resolut=""
            val jsonObject = JSONObject()
            jsonObject.put("name", "string")
            jsonObject.put("password", "string")
            val jsonObjectString = jsonObject.toString()
            try {
                GlobalScope.launch ( Dispatchers.IO) {
                    val url = URL("http://192.168.2.129:7105/api/Auth/")
                    val httpUrlConnection = url.openConnection() as HttpURLConnection
                    httpUrlConnection.requestMethod = "POST"
                    httpUrlConnection.setRequestProperty("Content-Type", "application/json")
                    httpUrlConnection.setRequestProperty("Accept", "text/plain")


                    var outputStream=httpUrlConnection.outputStream
                    var outputStreamWriter=OutputStreamWriter(outputStream)
                    outputStreamWriter.write(jsonObjectString)
                    outputStreamWriter.flush()

                    val inputStreamReader=httpUrlConnection.inputStream
                    val inputStramReader=InputStreamReader(inputStreamReader)
                    var data=inputStramReader.read()
                    while (data !=1){
                        resolut+=data.toChar()
                        data=inputStramReader.read()
                    }

                    if (httpUrlConnection.responseCode==200) {
                        var jsonObject=JSONObject(resolut)
                        var token=jsonObject.getString("token")
                        Log.e("Token",token)
                    } else {
                        Log.e("Token","User Not found")
                    }
                }
            } catch (e: Exception) {
                Log.e("HTTPURLCONNECTION_ERROR", e.toString())
            }
            return resolut
        }

        override fun onPostExecute(result: String?) {
        }
    }


}