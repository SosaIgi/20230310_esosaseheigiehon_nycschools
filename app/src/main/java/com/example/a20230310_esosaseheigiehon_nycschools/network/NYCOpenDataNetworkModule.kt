package com.example.a20230310_esosaseheigiehon_nycschools.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(ViewModelComponent::class)
@Module
object NYCOpenDataNetworkModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://data.cityofnewyork.us/resource/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesNYCOpenDataApi(retrofit: Retrofit): NYCOpenDataAPI{
        return retrofit.create(NYCOpenDataAPI::class.java)
    }

}