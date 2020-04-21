package com.dshovhenia.playgroundapp.data.cache.dao.video

import androidx.room.*
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideo

@Dao
abstract class VideoDao {

  @Query("SELECT * FROM " + DbConstants.VIDEO_TABLE_NAME)
  abstract fun getVideos(): List<CachedVideo>

  @Query("DELETE FROM " + DbConstants.VIDEO_TABLE_NAME)
  abstract fun clearVideos()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertVideo(cachedVideo: CachedVideo) : Long

}