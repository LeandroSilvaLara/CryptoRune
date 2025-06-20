package com.leandrocourse.core.data.local.database

interface DatabaseClient<T> {
    fun create(): T
}