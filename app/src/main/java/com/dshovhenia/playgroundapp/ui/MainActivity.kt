package com.dshovhenia.playgroundapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dshovhenia.playgroundapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.activity_main_fragment_container, HomeFragment(), HOME_TAG)
//                .commit()
//        }
    }

//    override fun startVideoFragment(vimeoVideo: VimeoVideo) {
//        supportFragmentManager.beginTransaction()
//            .replace(
//                R.id.activity_main_fragment_container,
//                VideoFragment.newInstance(vimeoVideo),
//                "video_tag"
//            )
//            .addToBackStack(null)
//            .commit()
//    }

//    companion object {
//        private const val HOME_TAG = "action_home"
//    }
}
