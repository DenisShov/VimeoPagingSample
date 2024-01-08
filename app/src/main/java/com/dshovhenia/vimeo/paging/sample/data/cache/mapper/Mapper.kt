package com.dshovhenia.vimeo.paging.sample.data.cache.mapper

interface Mapper<E, D> {

  fun mapFrom(type: E): D

  fun mapTo(type: D): E

}
