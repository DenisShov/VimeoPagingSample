package com.dshovhenia.vimeo.paging.sample.data.cache.dao.pictures

import androidx.room.*
import com.dshovhenia.vimeo.paging.sample.data.cache.db.DbConstants
import com.dshovhenia.vimeo.paging.sample.data.cache.model.pictures.CachedPictureSizes

@Dao
@Suppress("UnnecessaryAbstractClass")
abstract class PictureSizesDao {

  @Query("SELECT * FROM " + DbConstants.PICTURE_SIZES_TABLE_NAME)
  abstract fun getPictureSizes(): List<CachedPictureSizes>

  @Query("DELETE FROM " + DbConstants.PICTURE_SIZES_TABLE_NAME)
  abstract fun clearPictureSizes()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertPictureSizes(cachedPictureSizes: CachedPictureSizes): Long

  @Transaction
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertPictureSizes(cachedPictureSizes: List<CachedPictureSizes?>)

}
