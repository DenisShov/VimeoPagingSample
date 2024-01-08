package com.dshovhenia.vimeo.paging.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dshovhenia.vimeo.paging.sample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}
