package com.dshovhenia.playgroundapp.data.cache.dao.pictures

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictures

@Dao
abstract class PicturesDao {

  @Query("SELECT * FROM " + DbConstants.PICTURES_TABLE_NAME)
  abstract fun getPictures(): List<CachedPictures>

  @Query("DELETE FROM " + DbConstants.PICTURES_TABLE_NAME)
  abstract fun clearPictures()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertPictures(cachedVideo: CachedPictures) : Long

}