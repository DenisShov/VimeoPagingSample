package com.dshovhenia.vimeo.paging.sample.data.remote.authentication

import android.util.Base64
import dagger.Lazy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber
import com.dshovhenia.vimeo.paging.sample.BuildConfig
import com.dshovhenia.vimeo.paging.sample.data.cache.preferences.model.AccessToken
import com.dshovhenia.vimeo.paging.sample.data.cache.preferences.PreferencesHelper
import com.dshovhenia.vimeo.paging.sample.data.remote.service.VimeoApiService
import javax.inject.Inject

class VimeoServiceAuthenticator @Inject constructor(
  // Lazy computes its value on the first call to get() and remembers that same value for all subsequent calls to get().
    val vimeoApiService: Lazy<VimeoApiService>, val prefHelper: PreferencesHelper
) : Authenticator {

  private var newAccessToken: AccessToken? = null

  override fun authenticate(route: Route?, response: Response): Request? {
    Timber.i("calling authenticator - responseCount: %s", responseCount(response))
    // If we’ve failed 2 times, give up.
    if (responseCount(response) >= 2) {
      return null
    }

    // Get the new Unauthenticated access token
    runBlocking(Dispatchers.IO) {
      launch {
        runCatching {
          newAccessToken = vimeoApiService.get()
            .getUnauthenticatedToken(basicAuthorizationHeader(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET))
        }.onSuccess {
          Timber.i("Access token received successfully")
        }.onFailure {
          Timber.e("Failed to get access token")
        }
      }
    }

    var newRequest: Request? = null

    if (newAccessToken != null) {
      newRequest = response.request().newBuilder()
        .header("Authorization", newAccessToken!!.authorizationHeader).build()
      // We need to save the newAccessToken in SharedPref
      prefHelper.accessToken = newAccessToken!!
    } else {
      Timber.e("Failed to get Unauthenticated token")
    }
    return newRequest
  }

  private fun responseCount(response: Response?): Int {
    var res = response
    var result = 1
    while ((res?.priorResponse().also { res = it }) != null) {
      result++
    }
    return result
  }

  private fun basicAuthorizationHeader(clientId: String, clientSecret: String) =
    "basic " + Base64.encodeToString("$clientId:$clientSecret".toByteArray(), Base64.NO_WRAP)
}
