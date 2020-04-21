package com.dshovhenia.playgroundapp.data.cache.dao.video

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideoStats

@Dao
abstract class VideoStatsDao {

  @Query("SELECT * FROM " + DbConstants.VIDEO_STATS_TABLE_NAME)
  abstract fun getVideoStats(): List<CachedVideoStats>

  @Query("DELETE FROM " + DbConstants.VIDEO_STATS_TABLE_NAME)
  abstract fun clearVideoStats()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertVideoStats(videoStats: CachedVideoStats): Long

}