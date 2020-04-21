package com.dshovhenia.playgroundapp.data.cache.dao.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUserMetadata

@Dao
abstract class UserMetadataDao {

  @Query("SELECT * FROM " + DbConstants.USER_METADATA_TABLE_NAME)
  abstract fun getUserMetadata(): List<CachedUserMetadata>

  @Query("DELETE FROM " + DbConstants.USER_METADATA_TABLE_NAME)
  abstract fun clearUserMetadata()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertUserMetadata(cachedUserMetadata: CachedUserMetadata) : Long

}