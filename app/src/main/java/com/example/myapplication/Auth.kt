package com.example.myapplication

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

@Suppress("DEPRECATION")
class Auth(): AsyncTask<String, String, String>() {


    override fun doInBackground(vararg p0: String?): String {
        var resolut = ""
        try {
            val jsonObject = JSONObject()
            jsonObject.put("name", "string")
            jsonObject.put("password", "string")
            val jsonObjectString = jsonObject.toString()
            var httpUrlConnection: HttpURLConnection? = null
            try {
                val url = URL("http://192.168.2.129:7105/api/Auth/")
                httpUrlConnection = url.openConnection() as HttpURLConnection
                httpUrlConnection.requestMethod = "POST"
                httpUrlConnection.setRequestProperty("Content-Type", "application/json")
                httpUrlConnection.setRequestProperty("Accept", "text/plain")


                var outputStream = httpUrlConnection.outputStream
                var outputStreamWriter = OutputStreamWriter(outputStream)
                outputStreamWriter.write(jsonObjectString)
                outputStreamWriter.flush()

                val inputStreamReader = httpUrlConnection.inputStream
                val inputStramReader = InputStreamReader(inputStreamReader)
                var data = inputStramReader.read()
                while (data != 1) {
                    resolut += data.toChar()
                    data = inputStramReader.read()
                }

                if (httpUrlConnection.responseCode == 200) {
                    var jsonObject = JSONObject(resolut)
                    var token = jsonObject.getString("token")
                    Log.e("Token", token)
                } else {
                    Log.e("Token", "User Not found")
                }
            } catch (e: Exception) {
                Log.e("HTTPURLCONNECTION_ERROR", e.toString())
            }
            finally {
                httpUrlConnection?.disconnect()
            }

        }catch (e: Exception) {
            Log.e("HTTPURLCONNECTION_ERROR", e.toString())
        }
        Log.e("ErrorCon",resolut)
        return resolut
    }

    override fun onPostExecute(result: String?) {
        var jsonObject = JSONObject(result)
        var token = jsonObject.getString("token")
        Log.e("Token", token)
    }
}