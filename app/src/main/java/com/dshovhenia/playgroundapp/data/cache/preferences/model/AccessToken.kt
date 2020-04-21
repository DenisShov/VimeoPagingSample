package com.dshovhenia.playgroundapp.data.cache.preferences.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class AccessToken {

  @SerializedName(FIELD_ACCESS_TOKEN)
  @Expose
  var accessToken = ""
  @SerializedName(FIELD_TOKEN_TYPE)
  @Expose
  private var token_type = ""
  @SerializedName(FIELD_SCOPE)
  @Expose
  var scope = ""

  var tokenType: String
    get() {
      return tokenType.let {
        // OAuth requires uppercase Authorization HTTP header value for token type
        if (!Character.isUpperCase(it[0])) firstLetterToUpperCase(it) else it
      }
    }
    set(token_type) {
      this.token_type = token_type
    }

  private fun firstLetterToUpperCase(string: String) =
    string[0].toString().toUpperCase(Locale.getDefault()) + string.substring(1)

  val authorizationHeader: String
    get() = if (token_type.isEmpty()) "" else "$tokenType $accessToken"

  override fun toString(): String {
    return "AccessToken:\n- access_token: $accessToken\n- token_type: $tokenType\n- scope: $scope\n"
  }

  companion object {
    const val FIELD_ACCESS_TOKEN = "access_token"
    const val FIELD_TOKEN_TYPE = "token_type"
    const val FIELD_SCOPE = "scope"
  }
}