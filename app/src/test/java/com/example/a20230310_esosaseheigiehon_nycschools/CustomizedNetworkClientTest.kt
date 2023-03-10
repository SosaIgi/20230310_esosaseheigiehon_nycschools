package com.example.a20230310_esosaseheigiehon_nycschools

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.URL


class CustomizedNetworkClientTest {

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Test
    fun test() {
        runBlocking {
            val url = URL("https://data.cityofnewyork.us/resource/s3k6-pzi2.json")
            val json = url.readText()
            print(json)
        }
    }
}