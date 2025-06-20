package com.leandrocourse.core.data.remote.network

interface HttpClient {
    fun <T> create(clazz: Class<T>): T
}