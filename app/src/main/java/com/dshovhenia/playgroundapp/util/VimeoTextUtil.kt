package com.dshovhenia.playgroundapp.util

import android.view.View
import android.widget.TextView
import java.util.*

object VimeoTextUtil {

  fun generateIdFromUri(uri: String): Int {
    val paths = uri.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    return Integer.valueOf(paths[paths.size - 1])
  }

  fun formatSecondsToDuration(seconds: Int) =
    String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60)

  private fun minutesToTimePassed(minutes: Int) =
    if (minutes < 60) {
      String.format(Locale.getDefault(), "%d minute" + (if (minutes != 1) "s" else "") + " ago", minutes)
    } else {
      hoursToTimePassed(minutes / 60)
    }

  private fun hoursToTimePassed(hours: Int) =
    if (hours < 24) {
      String.format(Locale.getDefault(), "%d hour" + (if (hours != 1) "s" else "") + " ago", hours)
    } else {
      daysToTimePassed(hours / 24)
    }

  private fun daysToTimePassed(days: Int) =
    when {
      days < 30 -> {
        String.format(
          Locale.getDefault(), "%d day" + (if (days != 1) "s" else "") + " ago", days
        )
      }
      days < 365 -> {
        val months = days / 30
        String.format(
          Locale.getDefault(), "%d month" + (if (months != 1) "s" else "") + " ago", months
        )
      }
      else -> {
        val years = days / 365
        String.format(
          Locale.getDefault(), "%d year" + (if (years != 1) "s" else "") + " ago", years
        )
      }
    }

  private fun getDifferenceMinutes(d1: Date, d2: Date) =
    ((d2.time - d1.time) / (60 * 1000) + 1).toInt()

  fun dateCreatedToTimePassed(dateCreated: Date) =
    minutesToTimePassed(getDifferenceMinutes(dateCreated, Date()))

  fun formatVideoAgeAndPlays(plays: Long?, dateCreated: Date) =
    if (plays == null) {
      dateCreatedToTimePassed(dateCreated)
    } else {
      String.format(
        Locale.getDefault(), "%s ⋅ %s",
        formatIntMetric(plays, "play", "plays"),
        dateCreatedToTimePassed(dateCreated)
      )
    }

  fun formatVideoCountAndFollowers(videos: Int, followers: Int) =
    String.format(
      Locale.getDefault(), "%s ⋅ %s",
      formatIntMetric(videos.toLong(), "video", "videos"),
      formatIntMetric(followers.toLong(), "follower", "followers")
    )

  fun hideOrDisplayTextViewIfNullString(textView: TextView, text: String?) {
    textView.text = text
    textView.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
  }

  private fun formatIntMetric(value: Long, singularMetric: String, pluralMetric: String) =
    when {
      value < 1000 -> {
        String.format(Locale.getDefault(), "%d %s", value, if (value != 1L) pluralMetric else singularMetric)
      }
      value < 1000000 -> {
        String.format(Locale.getDefault(), "%.1fk %s", value.toDouble() / 1000, pluralMetric)
      }
      else -> {
        String.format(Locale.getDefault(), "%.1fm %s", value.toDouble() / 1000000, pluralMetric)
      }
    }

}