package com.example.myrecipes.ui.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myrecipes.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}