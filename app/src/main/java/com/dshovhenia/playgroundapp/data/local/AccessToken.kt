package com.dshovhenia.playgroundapp.data.local

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
      // OAuth requires uppercase Authorization HTTP header value for token type
      if (!Character.isUpperCase(token_type[0])) {
        token_type = Character.toString(token_type[0]).toUpperCase() + token_type.substring(1)
      }
      return token_type
    }
    set(token_type) {
      this.token_type = token_type
    }

  val authorizationHeader: String
    get() = if (token_type.isEmpty()) {
      ""
    } else "$tokenType $accessToken"

  override fun toString(): String {
    return "AccessToken:\n- access_token: $accessToken\n- token_type: $tokenType\n- scope: $scope\n"
  }

  companion object {
    const val FIELD_ACCESS_TOKEN = "access_token"
    const val FIELD_TOKEN_TYPE = "token_type"
    const val FIELD_SCOPE = "scope"
  }
}