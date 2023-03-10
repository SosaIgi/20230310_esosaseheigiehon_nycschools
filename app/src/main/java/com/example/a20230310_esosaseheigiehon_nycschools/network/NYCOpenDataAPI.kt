package com.example.a20230310_esosaseheigiehon_nycschools.network

import com.example.a20230310_esosaseheigiehon_nycschools.model.HighSchool
import com.example.a20230310_esosaseheigiehon_nycschools.model.SATScore
import retrofit2.http.GET

interface NYCOpenDataAPI {
    @GET("s3k6-pzi2.json")
    suspend fun highSchools(): List<HighSchool>

    @GET("f9bf-2cp4.json")
    suspend fun satScores(): List<SATScore>

}