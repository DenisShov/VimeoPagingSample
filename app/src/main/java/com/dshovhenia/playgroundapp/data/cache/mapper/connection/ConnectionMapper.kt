package com.dshovhenia.playgroundapp.data.cache.mapper.connection

import com.dshovhenia.playgroundapp.data.cache.mapper.Mapper
import com.dshovhenia.playgroundapp.data.cache.model.connection.CachedConnection
import com.dshovhenia.playgroundapp.data.model.Connection
import javax.inject.Inject

class ConnectionMapper @Inject constructor() :
  Mapper<CachedConnection, Connection> {

  override fun mapFrom(type: CachedConnection?): Connection? {
    return type?.let { Connection(it.uri, it.total) }
  }

  override fun mapTo(type: Connection?): CachedConnection? {
    return type?.let {
      CachedConnection(
        it.uri,
        it.total
      )
    }
  }
}