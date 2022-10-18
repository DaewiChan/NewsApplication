package com.daewi.newsapplication.common

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject


open class JSONUtil {

    fun parseError(errorJsonString: String) : String{
        if (errorJsonString != ""){
            val jsonObject = JSONObject(errorJsonString)
            var errorMessage = ""//jsonObject.getString("message")

            if (jsonObject.has("message")){
                errorMessage = jsonObject.getString("message")
            }

            if (jsonObject.has("errors")){
                val errorObj: JSONObject = jsonObject.getJSONObject("errors")
                //z val errorArr = jsonObject.getString("errors")
                errorMessage = JSONUtil().getErrorMessage(errorObj.toString())
                //return errArrMessage
            }
            return  errorMessage
        }else{
            return "Something went wrong!!"
        }
    }

    fun getErrorMessage(dynamicJSON: String) : String{

        val dynamicjson: JSONObject = JSONObject(dynamicJSON)

        var errorMessage = ""

        val keys: Iterator<*> = dynamicjson.keys()

        if (dynamicjson.has("message")){
            errorMessage = dynamicjson.getString("message")
        }

        while (keys.hasNext()) { // loop to get the dynamic key
            val currentDynamicKey = keys.next() as String
            // get the value of the dynamic key
            val currentValueRaw = dynamicjson.get(currentDynamicKey)
            if (currentValueRaw is JSONArray){
                val currentDynamicValue: JSONArray = dynamicjson.getJSONArray(currentDynamicKey)
                // do something here with the value... or either make another while loop to Iterate further
                val errString = currentDynamicValue.join("\n")

                Log.e("JSON Value", errString)
                return errString
            }else if (currentValueRaw is JSONObject){
                val currentDynamicValue: JSONObject = dynamicjson.getJSONObject(currentDynamicKey)
                // do something here with the value... or either make another while loop to Iterate further
                Log.e("JSON Value", currentDynamicValue.toString())
                return  currentDynamicValue.toString()
            }
        }
        //return ""
        return errorMessage
    }
}