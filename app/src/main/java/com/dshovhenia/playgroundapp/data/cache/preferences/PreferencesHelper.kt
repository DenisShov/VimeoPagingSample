package com.dshovhenia.playgroundapp.data.cache.preferences

import android.content.Context
import android.content.SharedPreferences
import com.dshovhenia.playgroundapp.data.cache.preferences.model.AccessToken
import javax.inject.Inject

class PreferencesHelper @Inject constructor(context: Context) {
  val sharedPreferences: SharedPreferences

  var accessToken: AccessToken
    get() {
      val accessToken =
        AccessToken()
      accessToken.accessToken =
        sharedPreferences.getString(
          AccessToken.FIELD_ACCESS_TOKEN,
          ""
        )!!
      accessToken.tokenType =
        sharedPreferences.getString(
          AccessToken.FIELD_TOKEN_TYPE,
          ""
        )!!
      accessToken.scope = sharedPreferences.getString(
        AccessToken.FIELD_SCOPE,
        ""
      )!!
      return accessToken
    }
    set(accessToken) {
      sharedPreferences.edit().putString(AccessToken.FIELD_ACCESS_TOKEN, accessToken.accessToken)
        .putString(AccessToken.FIELD_TOKEN_TYPE, accessToken.tokenType)
        .putString(AccessToken.FIELD_SCOPE, accessToken.scope).apply()
    }

  fun deleteAccessToken() {
    sharedPreferences.edit().remove(AccessToken.FIELD_ACCESS_TOKEN)
      .remove(AccessToken.FIELD_TOKEN_TYPE).remove(
        AccessToken.FIELD_SCOPE
      ).apply()
  }

  fun clear() {
    sharedPreferences.edit().clear().apply()
  }

  var videoLastCacheTime: Long
    get() = sharedPreferences.getLong(PREF_KEY_VIDEO_LAST_CACHE, 0)
    set(lastCache) = sharedPreferences.edit().putLong(PREF_KEY_VIDEO_LAST_CACHE, lastCache).apply()

  var commentLastCacheTime: Long
    get() = sharedPreferences.getLong(PREF_KEY_COMMENT_LAST_CACHE, 0)
    set(lastCache) = sharedPreferences.edit().putLong(PREF_KEY_COMMENT_LAST_CACHE, lastCache).apply()

  companion object {
    private const val PREF_FILE_NAME = "vimeo_pref_file"

    private val PREF_KEY_VIDEO_LAST_CACHE = "video_last_cache"
    private val PREF_KEY_COMMENT_LAST_CACHE = "comment_last_cache"
  }

  init {
    sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
  }
}