package com.dshovhenia.playgroundapp.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.dshovhenia.playgroundapp.data.model.VimeoConnection
import com.dshovhenia.playgroundapp.data.model.VimeoMetadataUser
import java.lang.reflect.Type

class VimeoMetadataUserDeserializer : JsonDeserializer<VimeoMetadataUser> {

  @Throws(JsonParseException::class)
  override fun deserialize(
    json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
  ): VimeoMetadataUser {
    val jsonObject = json.asJsonObject

    val jsonConnectionsObject = jsonObject.getAsJsonObject("connections")
    val followers = context.deserialize<VimeoConnection>(
      jsonConnectionsObject.get("followers"), VimeoConnection::class.java
    )
    val videos = context.deserialize<VimeoConnection>(
      jsonConnectionsObject.get("videos"), VimeoConnection::class.java
    )

    return VimeoMetadataUser(followers, videos)
  }
}
