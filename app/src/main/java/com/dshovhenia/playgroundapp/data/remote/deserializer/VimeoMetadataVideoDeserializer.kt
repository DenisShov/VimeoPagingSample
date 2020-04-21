package com.dshovhenia.playgroundapp.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.dshovhenia.playgroundapp.data.cache.model.connection.CachedConnection
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideoMetadata
import java.lang.reflect.Type

class VimeoMetadataVideoDeserializer : JsonDeserializer<CachedVideoMetadata> {

  @Throws(JsonParseException::class)
  override fun deserialize(
    json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
  ): CachedVideoMetadata {
    val jsonObject = json.asJsonObject

    val jsonConnectionsObject = jsonObject.getAsJsonObject("connections")
    val comments = context.deserialize<CachedConnection>(
      jsonConnectionsObject.get("comments"), CachedConnection::class.java
    )
    val likes = context.deserialize<CachedConnection>(
      jsonConnectionsObject.get("likes"), CachedConnection::class.java
    )

    return CachedVideoMetadata(comments, likes)
  }
}
