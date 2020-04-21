package com.dshovhenia.playgroundapp.data.cache.model.pictures

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants

@Entity(
  tableName = DbConstants.PICTURE_SIZES_TABLE_NAME,
  foreignKeys = [
    ForeignKey(
      entity = CachedPictures::class,
      parentColumns = ["id"],
      childColumns = ["picturesId"],
      onDelete = ForeignKey.CASCADE
    )]
)
class CachedPictureSizes(
  var width: Int = 0,
  var height: Int = 0,
  var link: String = ""
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
  var picturesId: Long = 0
}
