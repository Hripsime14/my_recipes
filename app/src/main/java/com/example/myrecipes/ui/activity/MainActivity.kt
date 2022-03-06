package com.example.myrecipes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myrecipes.R
import com.example.myrecipes.ui.common.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}