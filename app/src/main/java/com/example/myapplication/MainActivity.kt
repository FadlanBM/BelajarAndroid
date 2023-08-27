package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(),AsyncTaskCompleteListener {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showNameTextView.text=resources.getString(R.string.sapaan,"")
        binding.sentBtn.setOnClickListener {
            var n=MyTask()
            n.execute()
        }
    }

    override fun onTaskComplate(jsonObject: JSONObject){
    }
}


