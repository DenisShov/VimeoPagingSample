package com.dshovhenia.playgroundapp.ui.details

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.startActivity
import com.dshovhenia.playgroundapp.R
import com.dshovhenia.playgroundapp.data.model.VimeoVideo

class VideoActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_video)

    val fragment = supportFragmentManager.findFragmentById(R.id.activity_main_fragment_container)
    if (fragment == null) {

      val video = intent.getParcelableExtra<VimeoVideo>(EXTRA_VIMEO_VIDEO)!!

      supportFragmentManager.beginTransaction().add(
        R.id.activity_main_fragment_container,
        VideoFragment.newInstance(video),
        VideoFragment.FRAGMENT_VIDEO_TAG
      ).commit()
    }
  }

  companion object {
    private const val EXTRA_VIMEO_VIDEO = "vimeo_video"

    fun startActivity(context: Context, video: VimeoVideo) {
      context.startActivity<VideoActivity>(EXTRA_VIMEO_VIDEO to video)
    }
  }
}
