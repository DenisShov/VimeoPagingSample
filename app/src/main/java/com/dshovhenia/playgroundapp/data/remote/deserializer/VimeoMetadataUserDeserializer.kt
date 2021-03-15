package com.dshovhenia.playgroundapp.data.remote.deserializer

import com.dshovhenia.playgroundapp.data.model.Connection
import com.dshovhenia.playgroundapp.data.model.user.UserMetadata
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class VimeoMetadataUserDeserializer : JsonDeserializer<UserMetadata> {

  val KEY_CONNECTIONS = "connections"
  val KEY_FOLLOWERS = "followers"
  val KEY_VIDEOS = "videos"

  @Throws(JsonParseException::class)
  override fun deserialize(
    json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
  ): UserMetadata {
    val jsonObject = json.asJsonObject

    val jsonConnectionsObject = jsonObject.getAsJsonObject(KEY_CONNECTIONS)
    val followers = context.deserialize<Connection>(
      jsonConnectionsObject.get(KEY_FOLLOWERS), Connection::class.java
    )
    val videos = context.deserialize<Connection>(
      jsonConnectionsObject.get(KEY_VIDEOS), Connection::class.java
    )

    return UserMetadata(
      followers,
      videos
    )
  }
}
