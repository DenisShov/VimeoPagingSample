package com.dshovhenia.playgroundapp.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.dshovhenia.playgroundapp.data.cache.model.connection.CachedConnection
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUserMetadata
import java.lang.reflect.Type

class VimeoMetadataUserDeserializer : JsonDeserializer<CachedUserMetadata> {

  @Throws(JsonParseException::class)
  override fun deserialize(
    json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
  ): CachedUserMetadata {
    val jsonObject = json.asJsonObject

    val jsonConnectionsObject = jsonObject.getAsJsonObject("connections")
    val followers = context.deserialize<CachedConnection>(
      jsonConnectionsObject.get("followers"), CachedConnection::class.java
    )
    val videos = context.deserialize<CachedConnection>(
      jsonConnectionsObject.get("videos"), CachedConnection::class.java
    )

    return CachedUserMetadata(
      followers,
      videos
    )
  }
}
