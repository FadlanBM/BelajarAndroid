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
            MyTask().execute()
            Auth().execute()
            var progressDialog=ProgressDialog(this)
            progressDialog.setMessage("Sabar Bre")
            progressDialog.setCancelable(false)
        }
    }
}