package com.example.a20230310_esosaseheigiehon_nycschools.repo

import com.example.a20230310_esosaseheigiehon_nycschools.network.NYCOpenDataAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@InstallIn(ViewModelComponent::class)
@Module
object SchoolsRepositoryModule {
    @Provides
    fun providesSchoolRepository(api: NYCOpenDataAPI): SchoolsRepository{
        return SchoolsRepository(api)
    }
}