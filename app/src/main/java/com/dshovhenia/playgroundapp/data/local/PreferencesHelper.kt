package com.dshovhenia.playgroundapp.data.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesHelper @Inject constructor(context: Context) {
  val sharedPreferences: SharedPreferences

  var accessToken: AccessToken
    get() {
      val accessToken =
        AccessToken()
      accessToken.accessToken =
        sharedPreferences.getString(AccessToken.FIELD_ACCESS_TOKEN, TOKEN_EMPTY_FIELD)!!
      accessToken.tokenType =
        sharedPreferences.getString(AccessToken.FIELD_TOKEN_TYPE, TOKEN_EMPTY_FIELD)!!
      accessToken.scope = sharedPreferences.getString(AccessToken.FIELD_SCOPE, TOKEN_EMPTY_FIELD)!!
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
        AccessToken.FIELD_SCOPE).apply()
  }

  fun clear() {
    sharedPreferences.edit().clear().apply()
  }

  companion object {
    private const val PREF_FILE_NAME = "vimeo_pref_file"
    private const val TOKEN_EMPTY_FIELD = ""
  }

  init {
    sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
  }
}