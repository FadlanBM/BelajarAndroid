package com.example.myapplication

import android.content.Intent
import android.os.AsyncTask
import android.os.Parcel
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONStringer
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


@Suppress("DEPRECATION")
class MyTask() : AsyncTask<String, String, String>(){


    override fun doInBackground(vararg p0: String?):String {

        var result = ""

        try {
            val url: URL
            var urlConnection: HttpURLConnection? = null
            try {
                url = URL("http://192.168.2.129:7105/api/Idenetitas/2")
                //open a URL coonnection
                urlConnection = url.openConnection() as HttpURLConnection
                val `in` = urlConnection!!.inputStream
                val isw = InputStreamReader(`in`)
                var data = isw.read()

                while (data != -1) {
                    result += data.toChar()
                    data = isw.read()
                }

                // return the data to onPostExecute method
                return result
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("Error",e.toString())
            } finally {
                urlConnection?.disconnect()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "Exception: " + e.message
        }
        return result
    }

    protected override fun onPostExecute(s: String?) {
        // show results

        try {
            val jsonObject = JSONArray(s)
            Log.e("TAG", jsonObject.toString())


            //val jsonArray1 = jsonObject.getJSONArray("users")
            //val jsonObject1 = jsonArray1.getJSONObject(index_no)
            //val id = jsonObject1.getString("id")
           // val name = jsonObject1.getString("name")
            //val my_users = "User ID: $id\nName: $name"
            //Show the Textview after fetching data
            //resultsTextView.setVisibility(View.VISIBLE)


            //Display data with the Textview
            //resultsTextView.setText(my_users)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}