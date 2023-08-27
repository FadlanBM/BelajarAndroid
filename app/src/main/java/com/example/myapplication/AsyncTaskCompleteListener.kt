package com.example.myapplication

import org.json.JSONArray
import org.json.JSONObject

interface AsyncTaskCompleteListener {
    fun onTaskComplate(jsonObject: JSONObject)
}