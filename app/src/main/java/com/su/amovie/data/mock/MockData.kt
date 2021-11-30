package com.su.amovie.data.mock

import android.content.Context
import com.su.amovie.R

class MockData(val context: Context) {
    fun getSampleList(): Array<String> {

        // Return flower list from string resources
        return context.resources.getStringArray(R.array.sample_array)
    }
}