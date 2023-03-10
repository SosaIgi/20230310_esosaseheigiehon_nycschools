package com.example.a20230310_esosaseheigiehon_nycschools.repo

import com.example.a20230310_esosaseheigiehon_nycschools.model.HighSchool
import com.example.a20230310_esosaseheigiehon_nycschools.model.SATScore
import com.example.a20230310_esosaseheigiehon_nycschools.model.SchoolModel
import com.example.a20230310_esosaseheigiehon_nycschools.network.NYCOpenDataAPI

class SchoolsRepository (private val api: NYCOpenDataAPI){
    private var highSchools: List<HighSchool>? = null
    private var satScores: List<SATScore>? = null


    fun fetchSchools(highSchools: List<HighSchool>?, satScores: List<SATScore>?): List<SchoolModel> {
        if(highSchools == null || satScores == null) return listOf()
        val schoolsMap = mutableMapOf<String, SATScore>()
        for (satScore in satScores) {
            schoolsMap[satScore.dbn ?: ""] = satScore
        }
        return highSchools.map {
            SchoolModel(school = it, satScores = schoolsMap[it.dbn])
        }
    }

    suspend fun getSchoolModel(callback : SchoolsCallback){
        try {
            highSchools = api.highSchools()
            satScores = api.satScores()
            val result = fetchSchools(highSchools, satScores)
            if(result.isNotEmpty()){
                callback.onSuccess(result)
            }
        }
        catch (e: Exception){
            callback.onFailure(e)
        }
    }
    interface SchoolsCallback {
        fun onSuccess(schools: List<SchoolModel>)
        fun onFailure(e: Throwable)
    }
}