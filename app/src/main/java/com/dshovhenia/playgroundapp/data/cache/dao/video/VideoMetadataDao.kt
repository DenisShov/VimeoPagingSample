package com.dshovhenia.playgroundapp.data.cache.dao.video

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideoMetadata

@Dao
abstract class VideoMetadataDao {

  @Query("SELECT * FROM " + DbConstants.VIDEO_METADATA_TABLE_NAME)
  abstract fun getVideoMetadata(): List<CachedVideoMetadata>

  @Query("DELETE FROM " + DbConstants.VIDEO_METADATA_TABLE_NAME)
  abstract fun clearVideoMetadata()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertVideoMetadata(videoMetadata: CachedVideoMetadata): Long

}