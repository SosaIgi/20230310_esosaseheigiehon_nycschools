package com.example.a20230310_esosaseheigiehon_nycschools

import com.example.a20230310_esosaseheigiehon_nycschools.network.NYCOpenDataNetworkModule
import com.example.a20230310_esosaseheigiehon_nycschools.repo.SchoolsRepository
import com.example.a20230310_esosaseheigiehon_nycschools.repo.SchoolsRepositoryModule
import org.junit.Before
import org.junit.Test

class NYCOpenDataTest {
    //test repository
    private lateinit var repository: SchoolsRepository

    @Before
    fun setUp() {
        repository = SchoolsRepositoryModule.providesSchoolRepository(
            NYCOpenDataNetworkModule.providesNYCOpenDataApi(
                NYCOpenDataNetworkModule.providesRetrofit()
            )
        )
    }
    @Test
    fun testRepository() {

    }


}