package com.su.amovie

import android.content.Context

class Datasource(val context: Context) {
    fun getFlowerList(): Array<String> {

        // Return flower list from string resources
        return context.resources.getStringArray(R.array.flower_array)
    }
}