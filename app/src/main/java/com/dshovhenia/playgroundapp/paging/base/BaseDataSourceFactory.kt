package com.dshovhenia.playgroundapp.paging.base

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.DataSource.Factory

abstract class BaseDataSourceFactory<T : Parcelable> : Factory<String, T>() {
  val collectionDataSourceLiveData = MutableLiveData<BaseDataSource<T>>()

  override fun create(): DataSource<String, T> {
    val dataSource = generateDataSource()
    collectionDataSourceLiveData.postValue(dataSource)
    return dataSource
  }

  abstract fun generateDataSource(): BaseDataSource<T>
}