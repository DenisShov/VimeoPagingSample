package com.dshovhenia.playgroundapp.data.cache.dao.connection

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.connection.CachedConnection

@Dao
abstract class ConnectionDao {

  @Query("SELECT * FROM " + DbConstants.CONNECTION_TABLE_NAME)
  abstract fun getConnection(): CachedConnection

  @Query("DELETE FROM " + DbConstants.CONNECTION_TABLE_NAME)
  abstract fun clearConnections()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertConnection(connection: CachedConnection): Long

}