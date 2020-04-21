package com.dshovhenia.playgroundapp.data.cache.dao.comment

import androidx.room.*
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.comment.CachedComment

@Dao
abstract class CommentDao {

  @Query("SELECT * FROM " + DbConstants.COMMENT_TABLE_NAME)
  abstract fun getComments(): List<CachedComment>

  @Query("DELETE FROM " + DbConstants.COMMENT_TABLE_NAME)
  abstract fun clearComments()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertComment(cachedComment: CachedComment) : Long

}