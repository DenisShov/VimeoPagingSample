package com.dshovhenia.playgroundapp.data.cache.mapper

interface Mapper<E, D> {

  fun mapFrom(type: E): D

  fun mapTo(type: D): E

}
