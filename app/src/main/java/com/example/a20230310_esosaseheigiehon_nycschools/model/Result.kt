package com.example.a20230310_esosaseheigiehon_nycschools.model

enum class Result {
    SUCCESS,
    ERROR,
    LOADING,
    Ready
}

sealed class State(val message : Result){
    class Ready: State(Result.Ready)
    class Loading: State(Result.LOADING)
    class Success(val schools: List<SchoolModel>): State(Result.SUCCESS)
    class Failure(val Throwable: Throwable): State(Result.ERROR)
}