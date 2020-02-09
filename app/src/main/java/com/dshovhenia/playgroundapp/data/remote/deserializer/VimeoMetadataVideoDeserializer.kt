package com.dshovhenia.playgroundapp.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.dshovhenia.playgroundapp.data.model.VimeoConnection
import com.dshovhenia.playgroundapp.data.model.VimeoMetadataVideo
import java.lang.reflect.Type

class VimeoMetadataVideoDeserializer : JsonDeserializer<VimeoMetadataVideo> {

  @Throws(JsonParseException::class)
  override fun deserialize(
    json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
  ): VimeoMetadataVideo {
    val jsonObject = json.asJsonObject

    val jsonConnectionsObject = jsonObject.getAsJsonObject("connections")
    val comments = context.deserialize<VimeoConnection>(
      jsonConnectionsObject.get("comments"), VimeoConnection::class.java
    )
    val likes = context.deserialize<VimeoConnection>(
      jsonConnectionsObject.get("likes"), VimeoConnection::class.java
    )

    return VimeoMetadataVideo(comments, likes)
  }
}
