package com.example.cuswork

import android.content.Context
import android.util.Log
import com.example.cuswork.domain.Order
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class CalculatorManager {
    companion object {
        const val PREF_NAME = "calculator_manager_prefs"
        private var instance: CalculatorManager? = null

        fun newInstance(): CalculatorManager {
            return if(instance == null) {
                instance = CalculatorManager()
                instance!!
            } else {
                instance!!
            }
        }

    }

    val orderList: MutableList<Order> = mutableListOf()

    fun saveOrderList(activity: MainActivity) {
        val gson = Gson()
        val preferences = activity.getPreferences(Context.MODE_PRIVATE)
        with(preferences?.edit()) {
            val json = gson.toJson(orderList)
            this!!.putString(PREF_NAME, json)
            apply()

            Log.d("ORDER_LIST_SERIALIZE", json)
        }
    }

    fun loadOrderList(activity: MainActivity) {
        val gson = Gson()
        val preferences = activity.getPreferences(Context.MODE_PRIVATE)
        val prefValue = preferences.getString(PREF_NAME, "")
        if(prefValue != null && prefValue.isNotEmpty()) {
            val itemType = object : TypeToken<List<Order>>() {}.type

            orderList.addAll(gson.fromJson(prefValue, itemType))

            Log.d("ORDER_LIST_DESIRIALIZE", orderList.toString())
        }
    }
}