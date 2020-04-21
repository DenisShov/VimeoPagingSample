package com.dshovhenia.playgroundapp.data.cache.model.pictures

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUser
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideo

@Entity(
  tableName = DbConstants.PICTURES_TABLE_NAME,
  foreignKeys = [
    ForeignKey(
      entity = CachedUser::class,
      parentColumns = ["id"],
      childColumns = ["userId"],
      onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
      entity = CachedVideo::class,
      parentColumns = ["uri"],
      childColumns = ["videoId"],
      onDelete = ForeignKey.CASCADE
    )
  ]
)
class CachedPictures(
  var uri: String = "",
  var sizes: List<CachedPictureSizes> = ArrayList()
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
  var userId: Long = 0
  var videoId: Long = 0
}
